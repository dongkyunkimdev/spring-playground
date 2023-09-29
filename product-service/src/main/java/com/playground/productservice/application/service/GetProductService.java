package com.playground.productservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.productservice.application.port.in.usecase.GetProductUseCase;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductInfo;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.domain.Product;
import com.playground.productservice.domain.exception.ProductNotFoundException;
import com.playground.productservice.util.mapper.GetProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetProductService implements GetProductUseCase {

    private final ProductPersistencePort productPersistencePort;

    private final GetProductMapper mapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public GetProductInfo execute(GetProductCommand command) {
        Product savedProduct = productPersistencePort.searchProductById(command.productId())
            .orElseThrow(ProductNotFoundException::new);

        return mapper.entityToInfo(savedProduct);
    }

}
