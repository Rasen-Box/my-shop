package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponseDto {

    private String productName;
    private int quantity;
    private double price;       // цена за 1 товар
    private double totalPrice;
}
