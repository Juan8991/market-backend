package com.shopjr.market.web.controller;


import com.shopjr.market.domain.Product;
import com.shopjr.market.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RequestMapping("/products")
@RestController
public class ProductController {

    /**
     * inyección de dependencias del servicio
     */
    @Autowired
    private ProductService productService;

    @Operation(summary = "Get all supermarket products")
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){

        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") Long categoryId){
        return productService.getByCategory(categoryId).map(products->{
             return new ResponseEntity<>(products,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/one/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId){
        return productService.getProduct(productId).map(product -> {
            return new ResponseEntity<>(product,HttpStatus.FOUND);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){
        if(productService.save(product)!=null)return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity delete(@PathVariable("productId") Long productId) {
        if(productService.delete(productId)) return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
