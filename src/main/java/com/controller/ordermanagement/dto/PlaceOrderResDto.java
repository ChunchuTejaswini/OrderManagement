package com.controller.ordermanagement.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderResDto {

    private int orderId;
    private double totalPrice;

    private List<OrderItemResDto>orderItemResDtoList;
    private String message;
}
