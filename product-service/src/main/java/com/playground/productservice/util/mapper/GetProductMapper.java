package com.playground.productservice.util.mapper;

import com.playground.core.annotation.CustomMapper;
import com.playground.productservice.application.port.in.usecase.dto.GetProductInfo;
import com.playground.productservice.domain.Product;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductResponse;
import com.playground.productservice.util.mapper.mapstruct.GetProductMapStruct;
import lombok.RequiredArgsConstructor;

@CustomMapper
@RequiredArgsConstructor
public class GetProductMapper {

    private final GetProductMapStruct mapStruct;

    public GetProductInfo entityToInfo(Product product) {
        return mapStruct.entityToInfo(product);
    }

    public GetProductResponse infoToResponse(GetProductInfo info) {
        return mapStruct.infoToResponse(info);
    }

}
