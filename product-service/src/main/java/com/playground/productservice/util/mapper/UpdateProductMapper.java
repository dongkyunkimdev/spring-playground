package com.playground.productservice.util.mapper;

import com.playground.productservice.application.port.in.usecase.dto.UpdateProductInfo;
import com.playground.productservice.domain.Product;
import com.playground.productservice.util.mapper.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface UpdateProductMapper {

    UpdateProductInfo entityToInfo(Product product);

}
