package com.example.SimpleProductAPI.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SimpleProductAPI.model.Product;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final List<Product> store = new ArrayList<>();

    // 1. GET /products — вернуть список всех товаров
    @GetMapping
    public List<Product> list() {
        return store;
    }

    // 2. POST /products — добавить новый товар
    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product product) {
        store.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // 3. DELETE /products/{id} — удалить товар по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean removed = store.removeIf(p -> p.getId() == id);
        return removed
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
