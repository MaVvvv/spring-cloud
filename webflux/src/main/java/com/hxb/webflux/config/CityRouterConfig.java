package com.hxb.webflux.config;

import com.hxb.webflux.handler.CityHandler;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 路由器类
 *
 * @author Ma_wei
 * @since 2019-05-10 16:38
 */
@SpringBootConfiguration
public class CityRouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routeCity(CityHandler cityHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        cityHandler::helloCity);
    }
}
