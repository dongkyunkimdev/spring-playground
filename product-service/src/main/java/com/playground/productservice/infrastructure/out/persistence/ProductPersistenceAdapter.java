package com.playground.productservice.infrastructure.out.persistence;

import com.playground.core.annotation.PersistenceAdapter;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.domain.Product;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.infrastructure.dao.ProductCategoryRepository;
import com.playground.productservice.infrastructure.dao.ProductCategoryRepositorySupport;
import com.playground.productservice.infrastructure.dao.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {

    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    private final ProductCategoryRepositorySupport productCategoryRepositorySupport;

    @Override
    public Slice<ProductCategory> findProductCategoryListByIdRangeAndName(Long fromProductCategoryId, Long toProductCategoryId, String productCategoryName, Pageable pageable) {
        return productCategoryRepositorySupport.findAllByIdRangeAndName(fromProductCategoryId, toProductCategoryId, productCategoryName, pageable);
    }

    @Override
    public Optional<Product> findProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Optional<ProductCategory> findProductCategoryById(Long productCategoryId) {
        return productCategoryRepository.findById(productCategoryId);
    }

    @Override
    public boolean isExistsProductCategoryByName(String name) {
        return productCategoryRepository.existsByName(name);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteProductCategory(ProductCategory productCategory) {
        productCategoryRepository.delete(productCategory);
        productCategoryRepository.flush();
    }

}
