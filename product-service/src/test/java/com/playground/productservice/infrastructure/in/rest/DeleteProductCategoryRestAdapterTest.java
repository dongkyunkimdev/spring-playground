package com.playground.productservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.ErrorResponse;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.domain.exception.ProductErrorCode;
import com.playground.productservice.infrastructure.dao.ProductCategoryRepository;
import com.playground.productservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class DeleteProductCategoryRestAdapterTest extends ControllerTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    void 상품_카테고리_삭제_성공() throws Exception {
        // given
        ProductCategory savedProductCategory = productCategoryRepository.save(ProductCategory.builder().name("test category for delete").build());

        // when
        MockHttpServletRequestBuilder request = delete("/v1/product/category/{productCategoryId}", savedProductCategory.getProductCategoryId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mvc.perform(request);

        // then
        result.andExpect(status().isNoContent());
    }

    @Test
    void 상품_카테고리_삭제_실패_상품_카테고리가_존재하지_않음() throws Exception {
        // given
        final Long productCategoryId = 98123L;

        // when
        MockHttpServletRequestBuilder request = delete("/v1/product/category/{productCategoryId}", productCategoryId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mvc.perform(request);

        // then
        result.andExpect(status().isNotFound());

        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        ErrorResponse responseDto = objectMapper.readValue(responseMessage, new TypeReference<>() {
        });

        assertThat(responseDto.isSuccess()).isFalse();
        assertThat(responseDto.getStatus()).isEqualTo(404);
        assertThat(responseDto.getCode()).isEqualTo(ProductErrorCode.PRODUCT_CATEGORY_NOT_FOUND.getCode());
        assertThat(responseDto.getReason()).isEqualTo(ProductErrorCode.PRODUCT_CATEGORY_NOT_FOUND.getReason());
    }

    @Test
    void 상품_카테고리_삭제_실패_상품_카테고리가_참조되고_있음() throws Exception {
        // given
        final Long productCategoryId = 1L;

        // when
        MockHttpServletRequestBuilder request = delete("/v1/product/category/{productCategoryId}", productCategoryId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mvc.perform(request);

        // then
        result.andExpect(status().isConflict());

        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        ErrorResponse responseDto = objectMapper.readValue(responseMessage, new TypeReference<>() {
        });

        assertThat(responseDto.isSuccess()).isFalse();
        assertThat(responseDto.getStatus()).isEqualTo(409);
        assertThat(responseDto.getCode()).isEqualTo(ProductErrorCode.PRODUCT_CATEGORY_REFERENCED.getCode());
        assertThat(responseDto.getReason()).isEqualTo(ProductErrorCode.PRODUCT_CATEGORY_REFERENCED.getReason());
    }

}
