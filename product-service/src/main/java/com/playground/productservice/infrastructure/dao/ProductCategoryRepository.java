package com.playground.productservice.infrastructure.dao;

import com.playground.core.domain.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    boolean existsByName(String name);

}
