package com.playground.productservice.util.mapper;

import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryInfo;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryResponse;
import com.playground.productservice.util.mapper.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface GetProductCategoryMapper {

    ProductCategory commandToEntity(GetProductCategoryCommand command);

    GetProductCategoryInfo entityToInfo(ProductCategory productCategory);

    GetProductCategoryResponse infoToResponse(GetProductCategoryInfo info);

}
