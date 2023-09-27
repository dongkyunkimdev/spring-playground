package com.playground.productservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.productservice.application.port.in.usecase.DeleteProductUseCase;
import com.playground.productservice.application.port.in.usecase.dto.DeleteProductCommand;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.application.port.out.rest.OrderServiceExternalPort;
import com.playground.productservice.domain.Product;
import com.playground.productservice.domain.exception.ProductNotFoundException;
import com.playground.productservice.domain.exception.ProductReferencedException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteProductService implements DeleteProductUseCase {

    private final ProductPersistencePort productPersistencePort;

    private final OrderServiceExternalPort orderServiceExternalPort;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public void execute(DeleteProductCommand command) {
        Product savedProduct = productPersistencePort.findProductById(command.productId())
                .orElseThrow(ProductNotFoundException::new);

        if (isProductReferenced(command.productId())) {
            throw new ProductReferencedException();
        }

        productPersistencePort.deleteProduct(savedProduct);

    }

    private boolean isProductReferenced(Long productId) {
        return orderServiceExternalPort.isProductReferenced(productId);
    }

}
