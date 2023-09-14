package com.playground.productservice.infrastructure.dao;

import com.playground.productservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
