package com.playground.productservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.productservice.application.port.in.usecase.DeleteProductCategoryUseCase;
import com.playground.productservice.application.port.in.usecase.dto.DeleteProductCategoryCommand;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.domain.exception.ProductCategoryNotFoundException;
import com.playground.productservice.domain.exception.ProductCategoryReferencedException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteProductCategoryService implements DeleteProductCategoryUseCase {

    private final ProductPersistencePort productPersistencePort;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public void execute(DeleteProductCategoryCommand command) {
        ProductCategory savedProductCategory = productPersistencePort.findProductCategoryById(command.productCategoryId())
                .orElseThrow(ProductCategoryNotFoundException::new);

        try {
            productPersistencePort.deleteProductCategory(savedProductCategory);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("CONSTRAINT `fk_product_product_category_product_category_id`")) {
                throw new ProductCategoryReferencedException();
            } else {
                throw new IllegalStateException(e);
            }
        }
    }

}
