package com.controller.ordermanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="orderitem")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;
    private double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(Item item, double price, int quantity) {
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItem(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }
}
