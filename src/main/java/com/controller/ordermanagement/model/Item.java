package com.controller.ordermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
@Entity
@Table(name="items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String itemName;
    @Column(nullable = false)
    private int price;
    private int quantity;
    private double rating;
    private double discount;

    public Item( String itemName, int price, int quantity, double rating,double discount) {
        this.discount = discount;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.rating = rating;
    }
}
