package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartResponseDto {

    private List<CartItemResponseDto> items;
    private double totalPrice;
}
