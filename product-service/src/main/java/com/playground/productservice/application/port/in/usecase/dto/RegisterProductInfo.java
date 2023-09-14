package com.playground.productservice.application.port.in.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterProductInfo {

    private Long productId;

    private String name;

    private Long stock;

    private BigDecimal price;

    private CommonProductCategoryInfo productCategory;

}
