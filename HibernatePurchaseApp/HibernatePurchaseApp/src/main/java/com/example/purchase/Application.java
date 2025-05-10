package com.example.purchase;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.purchase.entity.Person;
import com.example.purchase.entity.Product;
import com.example.purchase.entity.Purchase;
import com.example.purchase.util.HibernateUtil;
public class Application {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("HibernatePurchaseApp запущено. Введите команду или /exit для выхода.");
            String line;
            while (!(line = scanner.nextLine().trim()).equalsIgnoreCase("/exit")) {
                String[] parts = line.split(" ");
                String cmd = parts[0];
                switch (cmd) {
                    case "/showProductsByPerson":
                        showProductsByPerson(sessionFactory, parts);
                        break;
                    case "/findPersonsByProductTitle":
                        findPersonsByProductTitle(sessionFactory, parts);
                        break;
                    case "/removePerson":
                        removePerson(sessionFactory, parts);
                        break;
                    case "/removeProduct":
                        removeProduct(sessionFactory, parts);
                        break;
                    case "/buy":
                        buy(sessionFactory, parts);
                        break;
                    default:
                        System.out.println("Неизвестная команда: " + cmd);
                }
                System.out.println("Введите следующую команду:");
            }
        }
        sessionFactory.close();
        System.out.println("Приложение завершено.");
    }

    private static void showProductsByPerson(SessionFactory sf, String[] parts) {
        if (parts.length < 2) {
            System.out.println("Использование: /showProductsByPerson <имя>");
            return;
        }
        String name = parts[1];
        try (Session session = sf.openSession()) {
            Person person = session.createQuery("from Person p where p.name = :name", Person.class)
                                     .setParameter("name", name)
                                     .uniqueResult();
            if (person == null) {
                System.out.println("Человек не найден: " + name);
                return;
            }
            List<Purchase> purchases = person.getPurchases();
            if (purchases.isEmpty()) {
                System.out.println("У " + name + " нет покупок.");
            } else {
                System.out.println("Покупки пользователя " + name + ":");
                purchases.forEach(p -> System.out.println(p.getProduct().getTitle()
                        + " по цене " + p.getPriceAtPurchase()));
            }
        }
    }

    private static void findPersonsByProductTitle(SessionFactory sf, String[] parts) {
        if (parts.length < 2) {
            System.out.println("Использование: /findPersonsByProductTitle <название>");
            return;
        }
        String title = parts[1];
        try (Session session = sf.openSession()) {
            List<Person> persons = session.createQuery(
                    "select distinct p.person from Purchase p where p.product.title = :title", Person.class)
                .setParameter("title", title)
                .list();
            if (persons.isEmpty()) {
                System.out.println("Никто не покупал продукт: " + title);
            } else {
                System.out.println("Покупатели продукта " + title + ":");
                persons.forEach(pr -> System.out.println(pr.getName()));
            }
        }
    }

    private static void removePerson(SessionFactory sf, String[] parts) {
        if (parts.length < 2) {
            System.out.println("Использование: /removePerson <имя>");
            return;
        }
        String name = parts[1];
        Transaction tx = null;
        try (Session session = sf.openSession()) {
            tx = session.beginTransaction();
            Person person = session.createQuery("from Person p where p.name = :name", Person.class)
                                     .setParameter("name", name)
                                     .uniqueResult();
            if (person == null) {
                System.out.println("Человек не найден: " + name);
            } else {
                session.remove(person);
                System.out.println("Удалён человек: " + name);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    private static void removeProduct(SessionFactory sf, String[] parts) {
        if (parts.length < 2) {
            System.out.println("Использование: /removeProduct <название>");
            return;
        }
        String title = parts[1];
        Transaction tx = null;
        try (Session session = sf.openSession()) {
            tx = session.beginTransaction();
            Product product = session.createQuery("from Product p where p.title = :title", Product.class)
                                      .setParameter("title", title)
                                      .uniqueResult();
            if (product == null) {
                System.out.println("Продукт не найден: " + title);
            } else {
                session.remove(product);
                System.out.println("Удалён продукт: " + title);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    private static void buy(SessionFactory sf, String[] parts) {
        if (parts.length < 3) {
            System.out.println("Использование: /buy <имя> <название>");
            return;
        }
        String name = parts[1], title = parts[2];
        Transaction tx = null;
        try (Session session = sf.openSession()) {
            tx = session.beginTransaction();
            Person person = session.createQuery("from Person p where p.name = :name", Person.class)
                                     .setParameter("name", name)
                                     .uniqueResult();
            Product product = session.createQuery("from Product p where p.title = :title", Product.class)
                                      .setParameter("title", title)
                                      .uniqueResult();
            if (person == null || product == null) {
                System.out.println("Пользователь или продукт не найдены.");
            } else {
                Purchase purchase = new Purchase();
                purchase.setPerson(person);
                purchase.setProduct(product);
                purchase.setPriceAtPurchase(product.getPrice());
                session.persist(purchase);
                System.out.println(name + " купил " + title);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
