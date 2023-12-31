package com.playground.productservice.util.mapper.mapstruct;

import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCategoryInfo;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryRequest;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryResponse;
import com.playground.productservice.util.mapper.mapstruct.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface RegisterProductCategoryMapStruct {

    RegisterProductCategoryCommand requestToCommand(RegisterProductCategoryRequest request);

    ProductCategory commandToEntity(RegisterProductCategoryCommand command);

    RegisterProductCategoryInfo entityToInfo(ProductCategory productCategory);

    RegisterProductCategoryResponse infoToResponse(RegisterProductCategoryInfo info);

}
