package com.shopjr.market.domain.repository;

import com.shopjr.market.domain.Product;
import com.shopjr.market.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(Long categoryId);
    Optional<List<Product>> getScaseProducts(Integer quantity);
    Optional<Product> getProduct(Long productId);
    Product save(Product product);
    void delete(Long productId);
}
