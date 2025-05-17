package com.example.springshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByPerson(Person person);
    List<Purchase> findByProduct(Product product);
}
