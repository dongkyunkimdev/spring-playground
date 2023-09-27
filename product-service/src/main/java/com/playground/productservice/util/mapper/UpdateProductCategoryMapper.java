package com.playground.productservice.util.mapper;

import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCategoryInfo;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.util.mapper.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface UpdateProductCategoryMapper {

    UpdateProductCategoryInfo entityToInfo(ProductCategory productCategory);

}
