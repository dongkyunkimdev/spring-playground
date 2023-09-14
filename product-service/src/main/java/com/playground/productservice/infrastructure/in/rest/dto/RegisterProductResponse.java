package com.playground.productservice.infrastructure.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterProductResponse {

    private Long productId;

    private String name;

    private Long stock;

    private BigDecimal price;

    private CommonProductCategoryResponse productCategory;

}
