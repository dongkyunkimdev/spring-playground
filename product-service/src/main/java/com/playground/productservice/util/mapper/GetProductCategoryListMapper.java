package com.playground.productservice.util.mapper;

import com.playground.core.annotation.CustomMapper;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListInfo;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.infrastructure.dao.dto.GetProductCategoryListSearchCondition;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryListRequest;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryListResponse;
import com.playground.productservice.util.mapper.mapstruct.GetProductCategoryListMapStruct;
import lombok.RequiredArgsConstructor;

@CustomMapper
@RequiredArgsConstructor
public class GetProductCategoryListMapper {

    private final GetProductCategoryListMapStruct mapStruct;

    public GetProductCategoryListCommand requestToCommand(GetProductCategoryListRequest request) {
        return mapStruct.requestToCommand(request);
    }

    public GetProductCategoryListInfo entityToInfo(ProductCategory productCategory) {
        return mapStruct.entityToInfo(productCategory);
    }

    public GetProductCategoryListResponse infoToResponse(GetProductCategoryListInfo info) {
        return mapStruct.infoToResponse(info);
    }

    public GetProductCategoryListSearchCondition commandToSearchCondition(GetProductCategoryListCommand command) {
        return mapStruct.commandToSearchCondition(command);
    }

}
