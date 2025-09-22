package com.shop.mapper;

import com.shop.dto.ProductRequestDto;
import com.shop.dto.ProductResponseDto;
import com.shop.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-22T00:00:51+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductRequestDto productRequestDto) {
        if ( productRequestDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productRequestDto.getName() );
        product.setDescription( productRequestDto.getDescription() );
        product.setPrice( productRequestDto.getPrice() );
        product.setStock( productRequestDto.getStock() );

        return product;
    }

    @Override
    public ProductResponseDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setName( product.getName() );
        productResponseDto.setDescription( product.getDescription() );
        productResponseDto.setPrice( product.getPrice() );
        productResponseDto.setStock( product.getStock() );

        return productResponseDto;
    }
}
