package com.playground.productservice.util.mapper;

import com.playground.core.annotation.CustomMapper;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCategoryInfo;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.infrastructure.in.rest.dto.UpdateProductCategoryRequest;
import com.playground.productservice.infrastructure.in.rest.dto.UpdateProductCategoryResponse;
import com.playground.productservice.util.mapper.mapstruct.UpdateProductCategoryMapStruct;
import lombok.RequiredArgsConstructor;

@CustomMapper
@RequiredArgsConstructor
public class UpdateProductCategoryMapper {

    private final UpdateProductCategoryMapStruct mapStruct;

    public UpdateProductCategoryCommand requestToCommand(Long productCategoryId, UpdateProductCategoryRequest request) {
        return mapStruct.requestToCommand(productCategoryId, request.name());
    }

    public UpdateProductCategoryInfo entityToInfo(ProductCategory productCategory) {
        return mapStruct.entityToInfo(productCategory);
    }

    public UpdateProductCategoryResponse infoToResponse(UpdateProductCategoryInfo info) {
        return mapStruct.infoToResponse(info);
    }

}
