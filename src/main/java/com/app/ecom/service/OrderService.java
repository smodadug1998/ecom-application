package com.app.ecom.service;

import com.app.ecom.response.OrderResponse;

import java.util.Optional;

public interface OrderService {
    Optional<OrderResponse> createOrder(String userId);
}
