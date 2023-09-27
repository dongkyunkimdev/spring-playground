package com.playground.core.paging;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.util.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryDslUtil {

    public static <T> OrderSpecifier[] createOrderSpecifiers(Class<? extends T> type, Pageable pageable) {
        final String variable = StringUtils.uncapitalize(type.getSimpleName());
        final List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
        final PathBuilder<T> entityPath = new PathBuilder<>(type, variable);

        for (Sort.Order order : pageable.getSort()) {
            if (hasField(type, order.getProperty())) {
                PathBuilder<Object> path = entityPath.get(order.getProperty());

                orderSpecifiers.add(new OrderSpecifier(Order.valueOf(order.getDirection().name()), path));
            }
        }

        return orderSpecifiers.toArray(OrderSpecifier[]::new);
    }

    private static <T> Boolean hasField(Class<? extends T> type, String name) {
        return Arrays.stream(type.getDeclaredFields())
            .anyMatch(field -> field.getName().equals(name));
    }

}
