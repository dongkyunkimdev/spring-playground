package com.playground.productservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.core.paging.SliceResponse;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class GetProductCategoryListRestAdapterTest extends ControllerTest {

    @Test
    void 상품_카테고리_리스트_조회_성공() throws Exception {
        // when
        MockHttpServletRequestBuilder requestBuilder = get("/v1/product/category")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        SuccessResponse responseDto = objectMapper.readValue(responseMessage, new TypeReference<>() {
        });

        assertThat(responseDto.isSuccess()).isTrue();
        assertThat(responseDto.getStatus()).isEqualTo(200);

        SliceResponse<ProductCategory> productCategorySliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(productCategorySliceResponse.getContent().size()).isEqualTo(10);
        assertThat(productCategorySliceResponse.getPage()).isEqualTo(0);
        assertThat(productCategorySliceResponse.getSize()).isEqualTo(10);
        assertThat(productCategorySliceResponse.isHasPrevious()).isEqualTo(false);
        assertThat(productCategorySliceResponse.isHasNext()).isEqualTo(true);

        List<ProductCategory> productCategoryList = productCategorySliceResponse.getContent();
        productCategoryList.forEach(productCategory -> {
            assertThat(productCategory.getProductCategoryId()).isNotNull();
            assertThat(productCategory.getName()).isNotNull();
        });
    }

    @Test
    void 상품_카테고리_리스트_조회_성공_fromId_필터링() throws Exception {
        // given
        final Long fromProductCategoryId = 1L;

        // when
        MockHttpServletRequestBuilder requestBuilder = get("/v1/product/category")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("fromProductCategoryId", String.valueOf(fromProductCategoryId));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        SuccessResponse responseDto = objectMapper.readValue(responseMessage, new TypeReference<>() {
        });

        assertThat(responseDto.isSuccess()).isTrue();
        assertThat(responseDto.getStatus()).isEqualTo(200);

        SliceResponse<ProductCategory> productCategorySliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(productCategorySliceResponse.getContent().size()).isEqualTo(10);
        assertThat(productCategorySliceResponse.getPage()).isEqualTo(0);
        assertThat(productCategorySliceResponse.getSize()).isEqualTo(10);
        assertThat(productCategorySliceResponse.isHasPrevious()).isEqualTo(false);
        assertThat(productCategorySliceResponse.isHasNext()).isEqualTo(true);

        List<ProductCategory> productCategoryList = productCategorySliceResponse.getContent();
        productCategoryList.forEach(productCategory -> {
            assertThat(productCategory.getProductCategoryId()).isGreaterThanOrEqualTo(fromProductCategoryId);
        });
    }

    @Test
    void 상품_카테고리_리스트_조회_성공_toId_필터링() throws Exception {
        // given
        final Long toProductCategoryId = 11L;

        // when
        MockHttpServletRequestBuilder requestBuilder = get("/v1/product/category")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("toProductCategoryId", String.valueOf(toProductCategoryId));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        SuccessResponse responseDto = objectMapper.readValue(responseMessage, new TypeReference<>() {
        });

        assertThat(responseDto.isSuccess()).isTrue();
        assertThat(responseDto.getStatus()).isEqualTo(200);

        SliceResponse<ProductCategory> productCategorySliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(productCategorySliceResponse.getContent().size()).isEqualTo(10);
        assertThat(productCategorySliceResponse.getPage()).isEqualTo(0);
        assertThat(productCategorySliceResponse.getSize()).isEqualTo(10);
        assertThat(productCategorySliceResponse.isHasPrevious()).isEqualTo(false);
        assertThat(productCategorySliceResponse.isHasNext()).isEqualTo(true);

        List<ProductCategory> productCategoryList = productCategorySliceResponse.getContent();
        productCategoryList.forEach(productCategory -> {
            assertThat(productCategory.getProductCategoryId()).isLessThanOrEqualTo(toProductCategoryId);
        });
    }

    @Test
    void 상품_카테고리_리스트_조회_성공_name_필터링() throws Exception {
        // given
        final String productCategoryName = "co";

        // when
        MockHttpServletRequestBuilder requestBuilder = get("/v1/product/category")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("productCategoryName", productCategoryName)
                .param("size", String.valueOf(1));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        SuccessResponse responseDto = objectMapper.readValue(responseMessage, new TypeReference<>() {
        });

        assertThat(responseDto.isSuccess()).isTrue();
        assertThat(responseDto.getStatus()).isEqualTo(200);

        SliceResponse<ProductCategory> productCategorySliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(productCategorySliceResponse.getContent().size()).isEqualTo(1);
        assertThat(productCategorySliceResponse.getPage()).isEqualTo(0);
        assertThat(productCategorySliceResponse.getSize()).isEqualTo(1);
        assertThat(productCategorySliceResponse.isHasPrevious()).isEqualTo(false);
        assertThat(productCategorySliceResponse.isHasNext()).isEqualTo(true);

        List<ProductCategory> productCategoryList = productCategorySliceResponse.getContent();
        productCategoryList.forEach(productCategory -> {
            assertThat(productCategory.getName()).containsIgnoringCase(productCategoryName);
        });
    }

    @Test
    void 상품_카테고리_리스트_조회_성공_fromId_toId_name_필터링() throws Exception {
        // given
        final Long fromProductCategoryId = 1L;
        final Long toProductCategoryId = 10L;
        final String productCategoryName = "es";

        // when
        MockHttpServletRequestBuilder requestBuilder = get("/v1/product/category")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("fromProductCategoryId", String.valueOf(fromProductCategoryId))
                .param("toProductCategoryId", String.valueOf(toProductCategoryId))
                .param("productCategoryName", productCategoryName)
                .param("size", String.valueOf(1));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        String responseMessage = result.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        SuccessResponse responseDto = objectMapper.readValue(responseMessage, new TypeReference<>() {
        });

        assertThat(responseDto.isSuccess()).isTrue();
        assertThat(responseDto.getStatus()).isEqualTo(200);

        SliceResponse<ProductCategory> productCategorySliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(productCategorySliceResponse.getContent().size()).isGreaterThanOrEqualTo(1);
        assertThat(productCategorySliceResponse.getPage()).isEqualTo(0);
        assertThat(productCategorySliceResponse.getSize()).isGreaterThanOrEqualTo(1);
        assertThat(productCategorySliceResponse.isHasPrevious()).isEqualTo(false);
        assertThat(productCategorySliceResponse.isHasNext()).isEqualTo(true);

        List<ProductCategory> productCategoryList = productCategorySliceResponse.getContent();
        productCategoryList.forEach(productCategory -> {
            assertThat(productCategory.getProductCategoryId()).isBetween(fromProductCategoryId, toProductCategoryId);
            assertThat(productCategory.getName()).containsIgnoringCase(productCategoryName);
        });
    }

}
