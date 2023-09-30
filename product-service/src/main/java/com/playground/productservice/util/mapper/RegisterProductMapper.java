package com.playground.productservice.util.mapper;

import com.playground.core.annotation.CustomMapper;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCommand;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductInfo;
import com.playground.productservice.domain.Product;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductRequest;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductResponse;
import com.playground.productservice.util.mapper.mapstruct.RegisterProductMapStruct;
import lombok.RequiredArgsConstructor;

@CustomMapper
@RequiredArgsConstructor
public class RegisterProductMapper {

    private final RegisterProductMapStruct mapStruct;

    public RegisterProductCommand requestToCommand(RegisterProductRequest request) {
        return mapStruct.requestToCommand(request);
    }

    public Product commandToEntity(RegisterProductCommand command, ProductCategory productCategory) {
        return Product.builder()
            .name(command.name())
            .stock(command.stock())
            .price(command.price())
            .productCategory(productCategory)
            .build();
    }

    public RegisterProductInfo entityToInfo(Product product) {
        return mapStruct.entityToInfo(product);
    }

    public RegisterProductResponse infoToResponse(RegisterProductInfo info) {
        return mapStruct.infoToResponse(info);
    }

}
