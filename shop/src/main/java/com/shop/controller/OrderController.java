package com.shop.controller;

import com.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {

    private  final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
}
