package com.playground.productservice.util.mapper.mapstruct;

import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCategoryInfo;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.infrastructure.in.rest.dto.UpdateProductCategoryResponse;
import com.playground.productservice.util.mapper.mapstruct.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface UpdateProductCategoryMapStruct {

    UpdateProductCategoryCommand requestToCommand(Long productCategoryId, String name);

    UpdateProductCategoryInfo entityToInfo(ProductCategory productCategory);

    UpdateProductCategoryResponse infoToResponse(UpdateProductCategoryInfo info);

}
