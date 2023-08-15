package com.playground.productservice.application.port.in.usecase.dto;

import com.playground.core.domain.product.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterProductCategoryInfo {

    private Long id;

    private String name;

    public static RegisterProductCategoryInfo from(ProductCategory productCategory) {
        return RegisterProductCategoryInfo.builder()
                .id(productCategory.getProductCategoryId())
                .name(productCategory.getName())
                .build();
    }

}
