package com.playground.productservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.ErrorResponse;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.productservice.domain.exception.ProductErrorCode;
import com.playground.productservice.infrastructure.in.rest.dto.GetProductCategoryResponse;
import com.playground.productservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static com.playground.productservice.support.ControllerTestUtil.createRequestBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class GetProductCategoryRestAdapterTest extends ControllerTest {

    @Test
    void 상품_카테고리_조회_성공() throws Exception {
        // given
        final Long productCategoryId = 1L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/product/category/{productCategoryId}", productCategoryId);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertThat(responseDto.isSuccess()).isTrue();
        assertThat(responseDto.getStatus()).isEqualTo(HttpStatus.OK.value());

        GetProductCategoryResponse productCategoryResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(productCategoryResponse.productCategoryId()).isEqualTo(productCategoryId);
        assertThat(productCategoryResponse.name()).isEqualTo("Clothing");
    }

    @Test
    void 상품_카테고리_조회_실패_상품_카테고리가_존재하지_않음() throws Exception {
        // given
        final Long productCategoryId = 98123L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/product/category/{productCategoryId}", productCategoryId);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isNotFound());

        ErrorResponse responseDto = getErrorResponse(result);
        ProductErrorCode errorCode = ProductErrorCode.PRODUCT_CATEGORY_NOT_FOUND;

        assertThat(responseDto.isSuccess()).isFalse();
        assertThat(responseDto.getStatus()).isEqualTo(errorCode.getStatus());
        assertThat(responseDto.getCode()).isEqualTo(errorCode.getCode());
        assertThat(responseDto.getReason()).isEqualTo(errorCode.getReason());
    }

}
