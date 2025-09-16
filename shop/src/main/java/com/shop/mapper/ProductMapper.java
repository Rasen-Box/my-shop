package com.shop.mapper;

import com.shop.dto.ProductRequestDto;
import com.shop.dto.ProductResponseDto;
import com.shop.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequestDto productRequestDto);
    ProductResponseDto toDto(Product product);
}
