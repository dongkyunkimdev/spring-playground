package com.playground.productservice.util.mapper;

import com.playground.core.annotation.CustomMapper;
import com.playground.productservice.application.port.in.usecase.dto.GetProductListCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductListInfo;
import com.playground.productservice.domain.Product;
import com.playground.productservice.infrastructure.dao.dto.GetProductListSearchCondition;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductListRequest;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductListResponse;
import com.playground.productservice.util.mapper.mapstruct.GetProductListMapStruct;
import lombok.RequiredArgsConstructor;

@CustomMapper
@RequiredArgsConstructor
public class GetProductListMapper {

    private final GetProductListMapStruct mapStruct;

    public GetProductListCommand requestToCommand(GetProductListRequest request) {
        return mapStruct.requestToCommand(request);
    }

    public GetProductListInfo entityToInfo(Product product) {
        return mapStruct.entityToInfo(product);
    }

    public GetProductListResponse infoToResponse(GetProductListInfo info) {
        return mapStruct.infoToResponse(info);
    }

    public GetProductListSearchCondition commandToSearchCondition(GetProductListCommand command) {
        return mapStruct.commandToSearchCondition(command);
    }

}
