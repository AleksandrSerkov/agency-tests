package com.example.BookStoreDBPractice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BookStoreDBPractice.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
}