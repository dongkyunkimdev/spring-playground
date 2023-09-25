package com.playground.productservice.application.port.in.usecase.dto;

public record DeleteProductCategoryInfo(
        Long productCategoryId,
        String name
) {

}
