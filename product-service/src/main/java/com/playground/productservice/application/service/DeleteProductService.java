package com.playground.productservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.productservice.application.port.in.usecase.DeleteProductUseCase;
import com.playground.productservice.application.port.in.usecase.dto.DeleteProductCommand;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.application.port.out.external.OrderServiceExternalPort;
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
        Product savedProduct = getProduct(command.productId());

        validateProductReferences(command.productId());
        deleteProduct(savedProduct);
    }

    private Product getProduct(Long productId) {
        return productPersistencePort.searchProductById(productId)
            .orElseThrow(ProductNotFoundException::new);
    }

    private void validateProductReferences(Long productId) {
        if (orderServiceExternalPort.isProductReferenced(productId)) {
            throw new ProductReferencedException();
        }
    }

    private void deleteProduct(Product savedProduct) {
        productPersistencePort.deleteProduct(savedProduct);
    }

}
