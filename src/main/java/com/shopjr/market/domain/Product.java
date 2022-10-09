package com.shopjr.market.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long productId;
    private String name;
    private Long categoryId;
    private Double price;
    private Integer stock;
    private Boolean active;
    private Category category;

}
