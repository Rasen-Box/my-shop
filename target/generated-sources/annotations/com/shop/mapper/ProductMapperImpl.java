package com.shop.mapper;

import com.shop.dto.ProductRequestDto;
import com.shop.dto.ProductResponseDto;
import com.shop.model.Category;
import com.shop.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-24T19:41:18+0300",
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

        productResponseDto.setCategoryName( productCategoryName( product ) );
        productResponseDto.setName( product.getName() );
        productResponseDto.setDescription( product.getDescription() );
        productResponseDto.setPrice( product.getPrice() );

        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> toDtoList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductResponseDto> list = new ArrayList<ProductResponseDto>( products.size() );
        for ( Product product : products ) {
            list.add( toDto( product ) );
        }

        return list;
    }

    private String productCategoryName(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
