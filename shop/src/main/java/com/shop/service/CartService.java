package com.shop.service;

import com.shop.dto.CartItemResponseDto;
import com.shop.dto.CartResponseDto;
import com.shop.exception.AppException;
import com.shop.model.Cart;
import com.shop.model.CartItem;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.repository.CartRepository;
import com.shop.repository.ProductRepository;
import com.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void addToCart(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("Пользователь не найден", HttpStatus.BAD_REQUEST));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException("Продукт не найден", HttpStatus.BAD_REQUEST));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            // если есть — увеличиваем количество
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            // если нет — создаём новый CartItem
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cart.getItems().add(newItem);
        }

        cartRepository.save(cart);

//        CartResponseDto responseDto = new CartResponseDto();
//        responseDto.setItems(cart.getItems());
//        responseDto.setTotalPrice();

//        return responseDto;
    }

    public CartResponseDto getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException("Cart not found", HttpStatus.BAD_REQUEST));

        List<CartItemResponseDto> items = cart.getItems().stream()
                .map(item -> {
                    CartItemResponseDto dto = new CartItemResponseDto();
                    dto.setProductName(item.getProduct().getName());
                    dto.setQuantity(item.getQuantity());
                    dto.setPrice(item.getProduct().getPrice());
                    dto.setTotalPrice(item.getProduct().getPrice() * item.getQuantity());
                    return dto;
                })
                .toList();

        double totalPrice = items.stream()
                .mapToDouble(CartItemResponseDto::getTotalPrice)
                .sum();

        CartResponseDto response = new CartResponseDto();
        response.setItems(items);
        response.setTotalPrice(totalPrice);

        return response;
    }

    public Cart getEntityCartByUser(User user) {

        return cartRepository.findByUser(user)
                .orElseThrow(() -> new AppException("Корзина не найдена", HttpStatus.BAD_REQUEST));
    }

    public void clearCartAfterOrder(Long cartId) {

        cartRepository.deleteById(cartId);
    }
}
