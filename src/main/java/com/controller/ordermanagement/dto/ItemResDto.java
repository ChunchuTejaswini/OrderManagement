package com.controller.ordermanagement.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResDto {
    private int itemId;
    private String itemName;
    private int price;
    private int quantity;
    private double rating;
    private double discount;

}
