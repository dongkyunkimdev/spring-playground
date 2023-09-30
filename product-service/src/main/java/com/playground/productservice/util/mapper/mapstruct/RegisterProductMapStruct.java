package com.playground.productservice.util.mapper.mapstruct;

import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCommand;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductInfo;
import com.playground.productservice.domain.Product;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductRequest;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductResponse;
import com.playground.productservice.util.mapper.mapstruct.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface RegisterProductMapStruct {

    RegisterProductCommand requestToCommand(RegisterProductRequest request);

    RegisterProductInfo entityToInfo(Product product);

    RegisterProductResponse infoToResponse(RegisterProductInfo info);

}
