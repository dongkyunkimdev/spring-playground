package com.playground.productservice.util.mapper.mapstruct;

import com.playground.productservice.application.port.in.usecase.dto.GetProductListCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductListInfo;
import com.playground.productservice.domain.Product;
import com.playground.productservice.infrastructure.dao.dto.GetProductListSearchCondition;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductListRequest;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductListResponse;
import com.playground.productservice.util.mapper.mapstruct.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface GetProductListMapStruct {

    GetProductListCommand requestToCommand(GetProductListRequest request);

    GetProductListInfo entityToInfo(Product product);

    GetProductListResponse infoToResponse(GetProductListInfo info);

    GetProductListSearchCondition commandToSearchCondition(GetProductListCommand command);

}
