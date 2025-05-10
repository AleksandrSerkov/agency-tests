package com.example.purchase.repository;

import java.util.List;

import entity.Person;
import entity.Product;
import jakarta.persistence.EntityManager;

public class ProductRepository {
    private final EntityManager em;

    public ProductRepository(EntityManager em) {
        this.em = em;
    }

    public Product findByTitle(String title) {
        return em.createQuery("SELECT p FROM Product p WHERE p.title = :title", Product.class)
                 .setParameter("title", title)
                 .getSingleResult();
    }

    public void removeByTitle(String title) {
        Product p = findByTitle(title);
        if (p != null) {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        }
    }

    public List<Person> findPersonsByProduct(String title) {
        return em.createQuery(
                "SELECT pur.person FROM Purchase pur WHERE pur.product.title = :title", Person.class)
                 .setParameter("title", title)
                 .getResultList();
    }
}
