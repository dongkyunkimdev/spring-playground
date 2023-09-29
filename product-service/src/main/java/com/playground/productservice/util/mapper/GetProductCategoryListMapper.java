package com.playground.productservice.util.mapper;

import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListInfo;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryListResponse;
import com.playground.productservice.util.mapper.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface GetProductCategoryListMapper {

    GetProductCategoryListInfo entityToInfo(ProductCategory productCategory);

    GetProductCategoryListResponse infoToResponse(GetProductCategoryListInfo info);

}
