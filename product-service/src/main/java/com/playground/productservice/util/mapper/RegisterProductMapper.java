package com.playground.productservice.util.mapper;

import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCommand;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductInfo;
import com.playground.productservice.domain.Product;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductRequest;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductResponse;
import com.playground.productservice.util.mapper.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface RegisterProductMapper {

    RegisterProductCommand requestToCommand(RegisterProductRequest request);

    @Mapping(source = "command.name", target = "name")
    @Mapping(source = "command.stock", target = "stock")
    @Mapping(source = "command.price", target = "price")
    @Mapping(source = "savedProductCategory", target = "productCategory")
    Product commandToEntity(RegisterProductCommand command, ProductCategory savedProductCategory);

    @Mapping(source = "productCategory.productCategoryId", target = "productCategory.productCategoryId")
    @Mapping(source = "productCategory.name", target = "productCategory.name")
    RegisterProductInfo entityToInfo(Product product);

    RegisterProductResponse infoToResponse(RegisterProductInfo info);

}