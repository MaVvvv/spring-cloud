package com.hxb.gateway.filter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-03 10:34
 */
@Component
public class ApiGlobalFilter implements GlobalFilter {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(ApiGlobalFilter.class);

    /**
     * 实现过滤器方法
     *
     * @param
     * @return 
     * @author Ma_wei
     * @since 2019/4/3
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String url = request.getURI().getPath();
        if (log.isDebugEnabled()) {
            log.debug("开始对请求地址：[{}]进行api过滤！",url);
        }
        String token = request.getQueryParams().getFirst("token");
        if (StringUtils.isBlank(token)) {
            Map<String,Object> messageMap = new HashMap<>(2);
            messageMap.put("status",-1);
            messageMap.put("data","鉴权失败！");
            byte[] bits = messageMap.toString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add("Content-Type", "text/json;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }
}
