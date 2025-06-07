package com.example.person_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.person_service.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}