package com.shop.service;

import com.shop.dto.ProductRequestDto;
import com.shop.dto.ProductResponseDto;
import com.shop.mapper.ProductMapper;
import com.shop.model.Product;
import com.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponseDto createProduct(ProductRequestDto dto) {

        Product product = productMapper.toEntity(dto);
        return productMapper.toDto(productRepository.save(product));
    }

    public Page<ProductResponseDto> getAllProducts(Long page, Long size, String sortBy, Long categoryId) {

        Pageable pageable = PageRequest.of(page.intValue(), size.intValue(), Sort.by(sortBy));

        Page<Product> products;

        if (categoryId != 0) {
            products = productRepository.findByCategoryId(categoryId, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        return products.map(productMapper::toDto);
    }

    public ProductResponseDto getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));
        return productMapper.toDto(product);
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        return productMapper.toDto(productRepository.save(product));
    }

    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

    public Page<ProductResponseDto> searchProducts(String query, Long page, Long size, String sortBy) {

        Pageable pageable = PageRequest.of(page.intValue(), size.intValue(), Sort.by(sortBy).ascending());

        Page<Product> products = productRepository
                .findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query, pageable);

        return products.map(productMapper::toDto);
    }

    public List<ProductResponseDto> getEntityProductsByCategoryId(Long categoryId) {

        List<Product> products = productRepository.findByCategoryId(categoryId);

        return productMapper.toDtoList(products);
    }
}
