package com.playground.productservice.application.port.in.usecase.dto;

import com.playground.core.domain.product.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterProductCategoryCommand {

    private String name;

    public ProductCategory toEntity() {
        return ProductCategory.builder()
                .name(this.name)
                .build();
    }

}
