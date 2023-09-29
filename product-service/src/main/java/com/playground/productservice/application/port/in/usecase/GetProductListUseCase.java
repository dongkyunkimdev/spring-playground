package com.playground.productservice.application.port.in.usecase;

import com.playground.productservice.application.port.in.usecase.dto.GetProductListCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductListInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GetProductListUseCase {

    Slice<GetProductListInfo> execute(GetProductListCommand command, Pageable pageable);

}
