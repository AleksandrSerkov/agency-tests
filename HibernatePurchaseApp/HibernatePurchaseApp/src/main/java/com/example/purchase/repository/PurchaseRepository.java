package com.example.purchase.repository;

import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PurchaseRepository {
    private final EntityManager em;

    public PurchaseRepository(EntityManager em) {
        this.em = em;
    }

    public void createPurchase(Person person, Product product, BigDecimal price) {
        em.getTransaction().begin();

        Purchase purchase = new Purchase();
        purchase.setPerson(person);
        purchase.setProduct(product);
        purchase.setPriceAtPurchase(price);
        purchase.setPurchasedAt(LocalDateTime.now());

        em.persist(purchase);

        em.getTransaction().commit();
    }

    public List<Product> findProductsByPerson(String personName) {
        return em.createQuery(
                "SELECT p.product FROM Purchase p WHERE p.person.name = :name", Product.class)
                .setParameter("name", personName)
                .getResultList();
    }

    public List<Person> findPersonsByProduct(String productTitle) {
        return em.createQuery(
                "SELECT p.person FROM Purchase p WHERE p.product.title = :title", Person.class)
                .setParameter("title", productTitle)
                .getResultList();
    }
}