package com.shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // корзина привязана к конкретному пользователю
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // список товаров в корзине
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    // общая сумма (опционально можно высчитывать динамически)
    private Double total;

}
