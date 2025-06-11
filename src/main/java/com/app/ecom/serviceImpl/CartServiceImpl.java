package com.app.ecom.serviceImpl;

import com.app.ecom.model.CartItem;
import com.app.ecom.model.Product;
import com.app.ecom.model.User;
import com.app.ecom.repo.CartItemRepo;
import com.app.ecom.repo.ProductRepo;
import com.app.ecom.repo.UserRepo;
import com.app.ecom.request.CartItemRequest;
import com.app.ecom.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final CartItemRepo cartItemRepo;


    @Override
    public boolean addToCart(String userId, CartItemRequest cartItemRequest) {
        Optional<Product> productOpt = productRepo.findById(cartItemRequest.getProductId());
        if (productOpt.isEmpty()) return false;

        Product product = productOpt.get();
        if (product.getStockQuantity() < cartItemRequest.getQuantity()) return false;

        Optional<User> userOpt = userRepo.findById(Long.valueOf(userId));
        if (userOpt.isEmpty()) return false;

        User user = userOpt.get();

        CartItem existingCartItem = cartItemRepo.findByUserAndProduct(user, product);
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartItemRepo.save(existingCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
            cartItemRepo.save(cartItem);
        }
        return true;
    }

    @Override
    public boolean deleteItemFromCart(String userId, Long productId) {
        Optional<Product> productOpt = productRepo.findById(productId);

        Optional<User> userOpt = userRepo.findById(Long.valueOf(userId));

        if (productOpt.isPresent() && userOpt.isPresent()) {
            cartItemRepo.deleteByUserAndProduct(userOpt.get(), productOpt.get());
            return true;
        }
        return false;
    }

    @Override
    public List<CartItem> getCart(String userId) {
        return userRepo.findById(Long.valueOf(userId))
                .map(cartItemRepo::findByUser)
                .orElseGet(List::of);
    }

    @Override
    public void clearCart(String userId) {
        userRepo.findById(Long.valueOf(userId))
                .ifPresent(cartItemRepo::deleteByUser
                );
    }
}
