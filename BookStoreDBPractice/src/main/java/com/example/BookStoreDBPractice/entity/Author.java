package com.example.BookStoreDBPractice.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    private String firstName;
    private String lastName;

    // геттеры и сеттеры
    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long id) { this.authorId = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String fn) { this.firstName = fn; }

    public String getLastName() { return lastName; }
    public void setLastName(String ln) { this.lastName = ln; }
}