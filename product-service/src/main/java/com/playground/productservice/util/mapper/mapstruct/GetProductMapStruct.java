package com.playground.productservice.util.mapper.mapstruct;

import com.playground.productservice.application.port.in.usecase.dto.GetProductInfo;
import com.playground.productservice.domain.Product;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductResponse;
import com.playground.productservice.util.mapper.mapstruct.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface GetProductMapStruct {

    GetProductInfo entityToInfo(Product product);

    GetProductResponse infoToResponse(GetProductInfo info);

}
