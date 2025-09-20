package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {

    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
