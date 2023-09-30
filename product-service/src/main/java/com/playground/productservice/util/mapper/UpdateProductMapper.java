package com.playground.productservice.util.mapper;

import com.playground.core.annotation.CustomMapper;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCommand;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductInfo;
import com.playground.productservice.domain.Product;
import com.playground.productservice.infrastructure.in.rest.dto.UpdateProductRequest;
import com.playground.productservice.infrastructure.in.rest.dto.UpdateProductResponse;
import com.playground.productservice.util.mapper.mapstruct.UpdateProductMapStruct;
import lombok.RequiredArgsConstructor;

@CustomMapper
@RequiredArgsConstructor
public class UpdateProductMapper {

    private final UpdateProductMapStruct mapStruct;

    public UpdateProductCommand requestToEntity(Long productId, UpdateProductRequest request) {
        return mapStruct.requestToEntity(productId, request);
    }

    public UpdateProductInfo entityToInfo(Product product) {
        return mapStruct.entityToInfo(product);
    }

    public UpdateProductResponse infoToResponse(UpdateProductInfo info) {
        return mapStruct.infoToResponse(info);
    }

}
