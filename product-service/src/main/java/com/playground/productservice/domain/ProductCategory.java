package com.playground.productservice.domain;

import com.playground.core.entity.BaseTimeEntity;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCategoryCommand;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCategory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    private Long productCategoryId;

    @Column(name = "name")
    private String name;

    @Builder
    public ProductCategory(String name) {
        this.name = name;
    }

    public void update(UpdateProductCategoryCommand command) {
        this.name = command.name();
    }

}
