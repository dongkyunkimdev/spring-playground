package com.playground.productservice.infrastructure.out.persistence;

import com.playground.core.annotation.PersistenceAdapter;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.domain.Product;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.infrastructure.dao.ProductCategoryRepository;
import com.playground.productservice.infrastructure.dao.ProductCategoryRepositorySupport;
import com.playground.productservice.infrastructure.dao.ProductRepository;
import com.playground.productservice.infrastructure.dao.ProductRepositorySupport;
import com.playground.productservice.infrastructure.dao.dto.GetProductCategoryListSearchCondition;
import com.playground.productservice.infrastructure.dao.dto.GetProductListSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {

    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    private final ProductRepositorySupport productRepositorySupport;

    private final ProductCategoryRepositorySupport productCategoryRepositorySupport;

    @Override
    public Slice<Product> searchProductListBySearchCondition(GetProductListSearchCondition searchCondition, Pageable pageable) {
        return productRepositorySupport.findListBySearchCondition(searchCondition, pageable);
    }

    @Override
    public Slice<ProductCategory> searchProductCategoryListBySearchCondition(GetProductCategoryListSearchCondition searchCondition, Pageable pageable) {
        return productCategoryRepositorySupport.findListBySearchCondition(searchCondition, pageable);
    }

    @Override
    public Optional<Product> searchProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Optional<ProductCategory> searchProductCategoryById(Long productCategoryId) {
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
