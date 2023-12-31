package com.playground.productservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.ErrorResponse;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.productservice.domain.exception.ProductErrorCode;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryRequest;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryResponse;
import com.playground.productservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static com.playground.productservice.support.AssertUtil.assertErrorResponse;
import static com.playground.productservice.support.AssertUtil.assertSuccessResponse;
import static com.playground.productservice.support.ControllerTestUtil.createRequestBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class RegisterProductCategoryRestAdapterTest extends ControllerTest {

    @Test
    void 상품_카테고리_등록_성공() throws Exception {
        // given
        final RegisterProductCategoryRequest requestDto = new RegisterProductCategoryRequest("Test Toys");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/products/categories")
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isCreated());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.CREATED);

        RegisterProductCategoryResponse productCategoryResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(productCategoryResponse.productCategoryId()).isNotNull();
        assertThat(productCategoryResponse.name()).isEqualTo(requestDto.name());
    }

    @Test
    void 상품_카테고리_등록_실패_이름이_중복됨() throws Exception {
        // given
        final RegisterProductCategoryRequest requestDto = new RegisterProductCategoryRequest("Clothing");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/products/categories")
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isConflict());

        ErrorResponse responseDto = getErrorResponse(result);
        assertErrorResponse(responseDto, ProductErrorCode.PRODUCT_CATEGORY_NAME_DUPLICATED);
    }

}
