package com.app.ecom.service;

import com.app.ecom.request.ProductRequest;
import com.app.ecom.response.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    Optional<ProductResponse> updateProduct(Long productId, ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    boolean deleteProduct(Long productId);

    List<ProductResponse> searchProducts(String keyword);
}
