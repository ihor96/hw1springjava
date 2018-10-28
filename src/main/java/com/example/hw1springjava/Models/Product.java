package com.example.hw1springjava.Models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    private String name;
    private int price;

    public Product() {
    }

    public Product(String type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }
}
