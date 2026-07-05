package com.controller.ordermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderReqDto {

    private List<OrderItemReqDto>orderItemReqDtoList;
    
}
