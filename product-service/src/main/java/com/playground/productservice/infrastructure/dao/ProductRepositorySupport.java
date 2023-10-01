package com.playground.productservice.infrastructure.dao;

import com.playground.core.paging.QueryDslUtil;
import com.playground.core.paging.SliceUtil;
import com.playground.productservice.domain.Product;
import com.playground.productservice.domain.QProduct;
import com.playground.productservice.infrastructure.dao.dto.GetProductListSearchCondition;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.playground.productservice.infrastructure.dao.support.ConditionBuilder.*;

@Repository
@RequiredArgsConstructor
public class ProductRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public Slice<Product> findListBySearchCondition(GetProductListSearchCondition searchCondition, Pageable pageable) {
        List<Product> content = queryFactory.selectFrom(QProduct.product)
            .where(
                productIdInRange(searchCondition.fromProductId(), searchCondition.toProductId()),
                productNameContains(searchCondition.productName()),
                priceInRange(searchCondition.minPrice(), searchCondition.maxPrice()),
                productCategoryIdInRange(searchCondition.fromProductCategoryId(), searchCondition.toProductCategoryId()),
                productCategoryNameContains(searchCondition.productCategoryName())
            )
            .orderBy(QueryDslUtil.createOrderSpecifiers(Product.class, pageable))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .fetch();

        return SliceUtil.createSlice(content, pageable);
    }

}
