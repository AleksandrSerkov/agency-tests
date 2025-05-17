package com.example.springshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);
}
