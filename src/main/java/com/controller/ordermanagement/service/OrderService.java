package com.controller.ordermanagement.service;

import com.controller.ordermanagement.dto.OrderItemResDto;
import com.controller.ordermanagement.dto.PlaceOrderReqDto;
import com.controller.ordermanagement.dto.PlaceOrderResDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderService {
    public PlaceOrderResDto placeOrder(PlaceOrderReqDto placeOrderReqDto);
    public  String cancelOrder(int orderId);
}
