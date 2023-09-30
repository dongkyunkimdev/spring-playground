package com.playground.productservice.util.mapper;

import com.playground.core.annotation.CustomMapper;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCategoryInfo;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryRequest;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryResponse;
import com.playground.productservice.util.mapper.mapstruct.RegisterProductCategoryMapStruct;
import lombok.RequiredArgsConstructor;

@CustomMapper
@RequiredArgsConstructor
public class RegisterProductCategoryMapper {

    private final RegisterProductCategoryMapStruct mapStruct;

    public RegisterProductCategoryCommand requestToCommand(RegisterProductCategoryRequest request) {
        return mapStruct.requestToCommand(request);
    }

    public ProductCategory commandToEntity(RegisterProductCategoryCommand command) {
        return mapStruct.commandToEntity(command);
    }

    public RegisterProductCategoryInfo entityToInfo(ProductCategory productCategory) {
        return mapStruct.entityToInfo(productCategory);
    }

    public RegisterProductCategoryResponse infoToResponse(RegisterProductCategoryInfo info) {
        return mapStruct.infoToResponse(info);
    }

}
