package com.playground.productservice.util.mapper.mapstruct;

import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListInfo;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.infrastructure.dao.dto.GetProductCategoryListSearchCondition;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryListRequest;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryListResponse;
import com.playground.productservice.util.mapper.mapstruct.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface GetProductCategoryListMapStruct {

    GetProductCategoryListCommand requestToCommand(GetProductCategoryListRequest request);

    GetProductCategoryListInfo entityToInfo(ProductCategory productCategory);

    GetProductCategoryListResponse infoToResponse(GetProductCategoryListInfo info);

    GetProductCategoryListSearchCondition commandToSearchCondition(GetProductCategoryListCommand command);

}
