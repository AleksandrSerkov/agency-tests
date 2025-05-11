package com.example;

import com.example.model.Item;
import com.example.model.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.concurrent.*;
import java.util.Random;

public class Main {
    private static final int THREAD_COUNT = 8;
    private static final int UPDATES_PER_THREAD = 20_000;
    private static final int TOTAL_ITEMS = 40;

    public static void main(String[] args) throws InterruptedException {
        // 1. Инициализация: очистка и заполнение таблицы
        initData();

        // 2. Параллельные потоки
        ExecutorService exec = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        Random rnd = new Random();

        for (int i = 0; i < THREAD_COUNT; i++) {
            exec.submit(() -> {
                for (int j = 0; j < UPDATES_PER_THREAD; j++) {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    
                    // Выбрать случайную запись по ID
                    long randomId = rnd.nextInt(TOTAL_ITEMS) + 1;
                    Item item = session.get(Item.class, randomId);
                    item.setVal(item.getVal() + 1);
                    
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    session.update(item);
                    tx.commit();
                    session.close();
                }
                latch.countDown();
            });
        }

        latch.await();
        exec.shutdown();

        // 3. Проверка суммы
        int sum = checkSum();
        System.out.println("Total sum of val = " + sum);
        HibernateUtil.shutdown();
    }

    private static void initData() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createQuery("DELETE FROM Item").executeUpdate();
        for (int i = 0; i < TOTAL_ITEMS; i++) {
            session.save(new Item(0));
        }
        tx.commit();
        session.close();
    }

    private static int checkSum() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int total = session.createQuery("SELECT sum(i.val) FROM Item i", Long.class)
                           .uniqueResult()
                           .intValue();
        tx.commit();
        session.close();
        return total;
    }
}
