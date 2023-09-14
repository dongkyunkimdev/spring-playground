package com.playground.productservice.domain;

import com.playground.core.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "stock")
    private Long stock;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @Builder
    public Product(String name, Long stock, BigDecimal price, ProductCategory productCategory) {
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.productCategory = productCategory;
    }

}
