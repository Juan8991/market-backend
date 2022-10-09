package com.shopjr.market.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private Long categoryId;
    private String category;
    private Boolean active;

}
