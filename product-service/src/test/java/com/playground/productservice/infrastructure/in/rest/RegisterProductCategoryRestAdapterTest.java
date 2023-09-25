package com.playground.productservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.ErrorResponse;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.productservice.domain.exception.ProductErrorCode;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryRequest;
import com.playground.productservice.infrastructure.in.rest.dto.RegisterProductCategoryResponse;
import com.playground.productservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class RegisterProductCategoryRestAdapterTest extends ControllerTest {

    @Test
    void 상품_카테고리_등록_성공() throws Exception {
        // given
        final RegisterProductCategoryRequest requestDto = new RegisterProductCategoryRequest("Test Toys");

        // when
        MockHttpServletRequestBuilder requestBuilder = post("/v1/product/category")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isCreated());

        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        SuccessResponse responseDto = objectMapper.readValue(responseMessage, new TypeReference<>() {
        });

        assertThat(responseDto.isSuccess()).isTrue();
        assertThat(responseDto.getStatus()).isEqualTo(201);

        RegisterProductCategoryResponse productCategoryResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(productCategoryResponse.productCategoryId()).isNotNull();
        assertThat(productCategoryResponse.name()).isEqualTo(requestDto.name());
    }

    @Test
    void 상품_카테고리_등록_실패_중복된_이름() throws Exception {
        // given
        final RegisterProductCategoryRequest requestDto = new RegisterProductCategoryRequest("Clothing");

        // when
        MockHttpServletRequestBuilder requestBuilder = post("/v1/product/category")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isConflict());

        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        ErrorResponse responseDto = objectMapper.readValue(responseMessage, new TypeReference<>() {
        });

        ProductErrorCode errorCode = ProductErrorCode.PRODUCT_CATEGORY_NAME_DUPLICATED;

        assertThat(responseDto.isSuccess()).isFalse();
        assertThat(responseDto.getStatus()).isEqualTo(errorCode.getStatus());
        assertThat(responseDto.getCode()).isEqualTo(errorCode.getCode());
        assertThat(responseDto.getReason()).isEqualTo(errorCode.getReason());
    }

}
