package com.controller.ordermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UpdateItemReqDto {

        private String itemName;
        private int price;
        private int quantity;
        private double rating;
        private double discount;

    }


