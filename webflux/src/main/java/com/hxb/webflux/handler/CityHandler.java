package com.hxb.webflux.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 测试处理器类
 *
 * @author Ma_wei
 * @since 2019-05-10 16:35
 */
@Component
public class CityHandler {

    public Mono<ServerResponse> helloCity(ServerRequest request) {
        System.out.println(request.session());
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello, City!"));
    }
}
