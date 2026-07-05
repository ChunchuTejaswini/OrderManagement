package com.controller.ordermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemReqDto {

    private int  itemId;
    private int quantity;
   // private double price;
    //private String itemName;
}
