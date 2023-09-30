package com.playground.productservice.util.mapper;

import com.playground.core.annotation.CustomMapper;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryInfo;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryResponse;
import com.playground.productservice.util.mapper.mapstruct.GetProductCategoryMapStruct;
import lombok.RequiredArgsConstructor;

@CustomMapper
@RequiredArgsConstructor
public class GetProductCategoryMapper {

    private final GetProductCategoryMapStruct mapStruct;

    public GetProductCategoryInfo entityToInfo(ProductCategory productCategory) {
        return mapStruct.entityToInfo(productCategory);
    }

    public GetProductCategoryResponse infoToResponse(GetProductCategoryInfo info) {
        return mapStruct.infoToResponse(info);
    }

}
