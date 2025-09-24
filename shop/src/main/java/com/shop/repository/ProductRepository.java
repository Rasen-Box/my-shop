package com.shop.repository;

import com.shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long category_id);
    Page<Product> findByCategoryId(Long category_id, Pageable pageable);
    Page<Product> findAll(Pageable pageable);
}
