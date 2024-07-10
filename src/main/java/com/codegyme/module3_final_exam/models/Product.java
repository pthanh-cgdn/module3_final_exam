package com.codegyme.module3_final_exam.models;

public class Product {
    private int id;
    private String name;
    private int price;
    private int discount;
    private int stock;

    public Product(int id, String name, int price, int discount, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
    }

    public Product(String name, int price, int discount, int stock) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
