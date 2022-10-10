package com.shopjr.market.domain.service;


import com.shopjr.market.domain.Product;
import com.shopjr.market.domain.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }
    public Optional<List<Product>> getByCategory(Long categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Optional<List<Product>> getScaseProducts(Integer quantity){
        return productRepository.getScaseProducts(quantity);
    }
    public Optional<Product> getProduct(Long productId){

        return productRepository.getProduct(productId);
    }
    public Product save(Product product){

        return productRepository.save(product);
    }
    public boolean delete(Long productId){
        return getProduct(productId).map(producto->{
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }
}
