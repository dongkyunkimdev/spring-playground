package com.playground.productservice.application.port.in.usecase;

import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryInfo;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryListCommand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GetProductCategoryListUseCase {

    Slice<GetProductCategoryInfo> execute(GetProductCategoryListCommand command, Pageable pageable);

}
