package com.playground.productservice.util.mapper;

import com.playground.productservice.application.port.in.usecase.dto.GetProductListCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductListInfo;
import com.playground.productservice.domain.Product;
import com.playground.productservice.infrastructure.dao.dto.GetProductListSearchCondition;
import com.playground.productservice.util.mapper.config.UnmappedIgnoreConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = UnmappedIgnoreConfig.class)
public interface GetProductListMapper {

    GetProductListInfo entityToInfo(Product product);

    GetProductListSearchCondition commandToSearchCondition(GetProductListCommand command);

}
