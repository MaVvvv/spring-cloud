package com.mxw.hxb.springboot.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 111
 *
 * @author Ma_wei
 * @since 2021/1/14 21:00
 */
@SpringBootConfiguration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
