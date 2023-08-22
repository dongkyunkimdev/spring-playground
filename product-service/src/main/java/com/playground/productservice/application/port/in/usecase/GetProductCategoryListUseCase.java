package com.playground.productservice.application.port.in.usecase;

import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryInfo;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListCommand;

import java.util.List;

public interface GetProductCategoryListUseCase {

    List<GetProductCategoryInfo> execute(GetProductCategoryListCommand command);

}
