package com.example.BookStoreDBPractice.entity;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String title;
    private BigDecimal price;
    private int stockQuantity;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Category category;

    // геттеры и сеттеры
    public Long getBookId() { return bookId; }
    public void setBookId(Long id) { this.bookId = id; }

    public String getTitle() { return title; }
    public void setTitle(String t) { this.title = t; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal p) { this.price = p; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int q) { this.stockQuantity = q; }

    public Author getAuthor() { return author; }
    public void setAuthor(Author a) { this.author = a; }

    public Category getCategory() { return category; }
    public void setCategory(Category c) { this.category = c; }
}
