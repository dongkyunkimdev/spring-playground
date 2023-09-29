package com.playground.productservice.application.port.in.usecase;

import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GetProductCategoryListUseCase {

    Slice<GetProductCategoryListInfo> execute(GetProductCategoryListCommand command, Pageable pageable);

}
