package com.playground.productservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.productservice.application.port.in.usecase.GetProductListUseCase;
import com.playground.productservice.application.port.in.usecase.dto.GetProductListCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductListInfo;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.domain.Product;
import com.playground.productservice.util.mapper.GetProductListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetProductListService implements GetProductListUseCase {

    private final ProductPersistencePort productPersistencePort;

    private final GetProductListMapper mapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Slice<GetProductListInfo> execute(GetProductListCommand command, Pageable pageable) {
        Slice<Product> productSlice = getProductSlice(command, pageable);

        return productSlice.map(mapper::entityToInfo);
    }

    private Slice<Product> getProductSlice(GetProductListCommand command, Pageable pageable) {
        return productPersistencePort.searchProductListBySearchCondition(mapper.commandToSearchCondition(command), pageable);
    }

}
