package com.shop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {

    @NotBlank
    private String name;
    private String description;

    @PositiveOrZero
    private Double price;
    private Integer stock;

}
