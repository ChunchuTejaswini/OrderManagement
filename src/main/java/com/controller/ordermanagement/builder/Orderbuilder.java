package com.controller.ordermanagement.builder;

import com.controller.ordermanagement.dto.OrderItemReqDto;
import com.controller.ordermanagement.dto.OrderItemResDto;
import com.controller.ordermanagement.dto.PlaceOrderReqDto;
import com.controller.ordermanagement.dto.PlaceOrderResDto;
import com.controller.ordermanagement.model.Item;
import com.controller.ordermanagement.model.Order;
import com.controller.ordermanagement.model.OrderItem;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Orderbuilder {

    public PlaceOrderResDto buildPlaceOrderResDto(Order order){

        List<OrderItemResDto> orderItemResDtoList = order
                .getOrderItems()
                .stream()
                .map(this::buildOrderItemResDto)
                .toList();
      return   PlaceOrderResDto
                .builder()
                .orderId(order.getOrderId())
                .totalPrice(order.getPrice())
              .orderItemResDtoList(orderItemResDtoList)
                .build();
    }


    public OrderItemResDto buildOrderItemResDto(OrderItem orderItem){

      return   OrderItemResDto
                .builder()
                .itemName(orderItem.getItem().getItemName())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getItem().getPrice())
                .subTotal(orderItem.getPrice())
                .build();

    }

    public OrderItem buildOrderItem(OrderItemReqDto orderItemReqDto,Item item,Order order,double price){


       return  OrderItem.builder()
                .item(item)
                .order(order)
                .price(price)
                .quantity(orderItemReqDto.getQuantity())
                .build();
    }



}
