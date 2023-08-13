package com.playground.core.domain.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "stock")
    private Long stock;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductCategory> productCategoryList;

}
