package com.playground.productservice.application.port.in.usecase.dto;

public record UpdateProductCategoryCommand(
        Long productCategoryId,
        String name
) {

}
