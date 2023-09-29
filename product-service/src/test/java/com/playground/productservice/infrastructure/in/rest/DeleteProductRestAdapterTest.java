package com.playground.productservice.infrastructure.in.rest;

import com.playground.productservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static com.playground.productservice.support.ControllerTestUtil.createRequestBuilder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class DeleteProductRestAdapterTest extends ControllerTest {

    @Test
    void 상품_삭제_성공() throws Exception {
        // given
        final Long productId = 1L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.DELETE, "/v1/products/{productId}", productId);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isNoContent());
    }

}
