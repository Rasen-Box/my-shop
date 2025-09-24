package com.shop.service;

import com.shop.model.Cart;
import com.shop.model.Order;
import com.shop.model.OrderItem;
import com.shop.model.User;
import com.shop.model.enums.OrderStatus;
import com.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserService userService;

    public void createOrder(Long userId) { //OrderResponseDto вместо void

        User user = userService.getUserById(userId);

        Cart cart = cartService.getEntityCartByUser(user);
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PROCESSING);
        order.setLocalDateTime(LocalDateTime.now());

        List<OrderItem> orderItems = cart.getItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    return orderItem;
                })
                .toList();

        order.setItems(orderItems);

        cartService.clearCartAfterOrder(cart.getId());

        orderRepository.save(order);
    }

}
