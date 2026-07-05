package com.controller.ordermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemReqDto {

    private String itemName;
    private int price;
    private int quantity;
    private double discount;

}
