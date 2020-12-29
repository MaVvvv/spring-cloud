package com.hxb.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.headers.HttpHeadersFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

/**
 * 请求头信息过滤器
 *
 * @author Ma_wei
 * @since 2020/9/30 14:12
 */
@Slf4j
@Component
public class ApiHeadersFilter implements HttpHeadersFilter {

    @Override
    public HttpHeaders filter(HttpHeaders input, ServerWebExchange exchange) {
        MediaType contentType = input.getContentType();
        if (contentType == MediaType.APPLICATION_FORM_URLENCODED ) {
            input.setContentType(MediaType.APPLICATION_JSON);
        }
        Mono<MultiValueMap<String, String>> formData = exchange.getFormData();
        Map<String, String> stringStringMap = Objects.requireNonNull(formData.block()).toSingleValueMap();
        log.info(stringStringMap.toString());
        return input;
    }
}
