package com.playground.productservice.util.mapper;

import com.playground.core.domain.product.ProductCategory;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCategoryInfo;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryRequest;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryResponse;
import com.playground.productservice.util.mapper.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface RegisterProductCategoryMapper {

    RegisterProductCategoryCommand requestToCommand(RegisterProductCategoryRequest request);

    ProductCategory commandToEntity(RegisterProductCategoryCommand command);

    RegisterProductCategoryInfo entityToInfo(ProductCategory productCategory);

    RegisterProductCategoryResponse infoToResponse(RegisterProductCategoryInfo info);

}
