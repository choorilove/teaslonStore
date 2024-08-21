package com.example.teastore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String id_item, description = null, customer_number,customer_email, customer_name, customer_surname,city,post_dep;
    private float price;

    public Order(String id_item, String description, String customer_number, String customer_email, String customer_name, String customer_surname, String city, String post_dep, float price) {
        this.id_item = id_item;
        this.description = description;
        this.customer_number = customer_number;
        this.customer_email = customer_email;
        this.customer_name = customer_name;
        this.customer_surname = customer_surname;
        this.city = city;
        this.post_dep = post_dep;
        this.price = price;
    }

    public Order(){}

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getId_item() {
        return id_item;
    }

    public void setId_item(String id_item) {
        this.id_item = id_item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(String customer_number) {
        this.customer_number = customer_number;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_surname() {
        return customer_surname;
    }

    public void setCustomer_surname(String customer_surname) {
        this.customer_surname = customer_surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPost_dep() {
        return post_dep;
    }

    public void setPost_dep(String post_dep) {
        this.post_dep = post_dep;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
