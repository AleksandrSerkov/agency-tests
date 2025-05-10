package com.example.purchase.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.List;
import com.example.purchase.entity.Person;

public class PersonRepository {
    private final EntityManager em;

    public PersonRepository(EntityManager em) {
        this.em = em;
    }

    public Person findByName(String name) {
        return em.createQuery("SELECT p FROM Person p WHERE p.name = :name", Person.class)
                 .setParameter("name", name)
                 .getSingleResult();
    }

    public void removeByName(String name) {
        Person p = findByName(name);
        if (p != null) {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        }
    }

    public List<Product> findProductsByPerson(String personName) {
        return em.createQuery(
                "SELECT pur.product FROM Purchase pur WHERE pur.person.name = :name", Product.class)
                 .setParameter("name", personName)
                 .getResultList();
    }
}
