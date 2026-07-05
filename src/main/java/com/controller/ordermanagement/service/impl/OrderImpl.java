package com.controller.ordermanagement.service.impl;

import com.controller.ordermanagement.builder.Orderbuilder;
import com.controller.ordermanagement.dao.ItemRepo;
import com.controller.ordermanagement.dao.OrderRepo;
import com.controller.ordermanagement.dto.OrderItemReqDto;
import com.controller.ordermanagement.dto.OrderItemResDto;
import com.controller.ordermanagement.dto.PlaceOrderReqDto;
import com.controller.ordermanagement.dto.PlaceOrderResDto;
import com.controller.ordermanagement.model.Item;
import com.controller.ordermanagement.model.Order;
import com.controller.ordermanagement.model.OrderItem;
import com.controller.ordermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderImpl implements OrderService {

    @Autowired
    ItemRepo itemRepo;
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    Orderbuilder orderbuilder;

    @Override
    public PlaceOrderResDto placeOrder(PlaceOrderReqDto placeOrderReqDto) {

        List<OrderItemReqDto> orderItemReqDtoList = placeOrderReqDto.getOrderItemReqDtoList();

        List<OrderItem> orderItemList=new ArrayList<OrderItem>();
        Order order=new Order();
        double finalPrice=0;
        PlaceOrderResDto placeOrderResDto=new PlaceOrderResDto();

        for(OrderItemReqDto orderItemReqDto:orderItemReqDtoList){
            Item item = itemRepo.findById(orderItemReqDto.getItemId()).get();
            double itemPrice=item.getPrice() * orderItemReqDto.getQuantity();
/*           OrderItem orderItem=new OrderItem();
           orderItem.setItem(item);
           orderItem.setOrder(order);*/

          //  PlaceOrderResDto placeOrderResDto=new PlaceOrderResDto();
           if(orderItemReqDto.getQuantity()>item.getQuantity()) {
               placeOrderResDto.setMessage(
                       item.getItemName() + " stock not available"
               );
               continue;
           }
            OrderItem orderItem = orderbuilder.buildOrderItem(orderItemReqDto, item, order, itemPrice);

            // orderItem.setQuantity(orderItemReqDto.getQuantity());

              // orderItem.setPrice(itemPrice);
               finalPrice+=itemPrice;
               int i = item.getQuantity() - orderItemReqDto.getQuantity();
               item.setQuantity(i);
               itemRepo.save(item);

            orderItemList.add(orderItem);
        }

        order.setOrderItems(orderItemList);
        order.setPrice(finalPrice);

        Order savedOrder = orderRepo.save(order);
        return orderbuilder.buildPlaceOrderResDto(savedOrder);


        //PlaceOrderResDto placeOrderResDto=new PlaceOrderResDto();
/*
        List<OrderItemResDto> orderItemResDtoList = new ArrayList<OrderItemResDto>();

        List<OrderItem> orderItems = savedOrder.getOrderItems();

        for(OrderItem orderItem:orderItems){*/

            /*OrderItemResDto orderItemResDto=new OrderItemResDto();

            orderItemResDto.setItemName(orderItem.getItem().getItemName());
            orderItemResDto.setQuantity(orderItem.getQuantity());
            orderItemResDto.setPrice(orderItem.getItem().getPrice());
            orderItemResDto.setSubTotal(orderItem.getPrice());
*//*
            OrderItemResDto orderItemResDto = orderbuilder.buildOrderItemResDto(orderItem);
            orderItemResDtoList.add(orderItemResDto);*/

       /* List<OrderItemResDto> orderItemResDtoList = savedOrder
                .getOrderItems()
                .stream()
                .map(orderbuilder::buildOrderItemResDto)
                .toList();*/

     /*   placeOrderResDto.setOrderId(order.getOrderId());
        placeOrderResDto.setOrderItemResDtoList(orderItemResDtoList);
        placeOrderResDto.setTotalPrice(order.getPrice());

        return placeOrderResDto;*/

    }

    @Override
    public String cancelOrder(int orderId) {
        Order order = orderRepo.findById(orderId).get();
        List<OrderItem> orderItems = order.getOrderItems();
        for(OrderItem orderItem:orderItems){
            Item item = orderItem.getItem();
            int i = item.getQuantity() + orderItem.getQuantity();
            item.setQuantity(i);
            itemRepo.save(item);
        }
        orderRepo.deleteById(orderId);
        return "The order got cancelled";
    }


}
