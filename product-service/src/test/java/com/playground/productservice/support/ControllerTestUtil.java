package com.playground.productservice.support;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class ControllerTestUtil {

    public static MockHttpServletRequestBuilder createRequestBuilder(HttpMethod method, String url, Object... pathVariables) {
        MockHttpServletRequestBuilder requestBuilder = null;

        if (method.equals(HttpMethod.GET)) {
            requestBuilder = get(url, pathVariables);
        } else if (method.equals(HttpMethod.POST)) {
            requestBuilder = post(url, pathVariables);
        } else if (method.equals(HttpMethod.PATCH)) {
            requestBuilder = patch(url, pathVariables);
        } else if (method.equals(HttpMethod.DELETE)) {
            requestBuilder = delete(url, pathVariables);
        }

        if (requestBuilder != null) {
            requestBuilder
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON);
        }

        return requestBuilder;
    }

}
