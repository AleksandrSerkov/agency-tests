package com.example.SimpleProductAPI.model;

public class Product {
    private static long counter = 0;

    private final long id;
    private String name;
    private int price;

    public Product() {
        this.id = ++counter;
    }

    public Product(String name, int price) {
        this.id = ++counter;
        this.name = name;
        this.price = price;
    }

    // Геттеры и сеттеры
    public long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
