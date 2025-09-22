package com.shop.controller;

import com.shop.dto.UserRequestDto;
import com.shop.model.User;
import com.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {

    private  final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("{userId}")
    public void createOrder(@PathVariable Long userId) {

        orderService.createOrder(userId);
    }


}
