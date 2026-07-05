package com.controller.ordermanagement.controller;

import com.controller.ordermanagement.dto.OrderItemResDto;
import com.controller.ordermanagement.dto.PlaceOrderReqDto;
import com.controller.ordermanagement.dto.PlaceOrderResDto;
import com.controller.ordermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public PlaceOrderResDto placeOrder(@RequestBody  PlaceOrderReqDto placeOrderReqDto){

        return orderService.placeOrder(placeOrderReqDto);
    }

    @PostMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable int id){

        return orderService.cancelOrder(id);
    }
}
