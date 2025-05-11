package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int val;

    public Item() {}
    public Item(int val) { this.val = val; }

    public Long getId() { return id; }
    public int getVal() { return val; }
    public void setVal(int val) { this.val = val; }
}
