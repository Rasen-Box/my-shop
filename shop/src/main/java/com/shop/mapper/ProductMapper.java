package com.shop.mapper;

import com.shop.dto.ProductRequestDto;
import com.shop.dto.ProductResponseDto;
import com.shop.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductRequestDto productRequestDto);

    @Mapping(target = "categoryName", source = "category.name")
    ProductResponseDto toDto(Product product);

    List<ProductResponseDto> toDtoList(List<Product> products);
}
