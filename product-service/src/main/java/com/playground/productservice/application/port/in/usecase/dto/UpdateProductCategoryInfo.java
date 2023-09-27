package com.playground.productservice.application.port.in.usecase.dto;

public record UpdateProductCategoryInfo(
        Long productCategoryId,
        String name
) {

}
