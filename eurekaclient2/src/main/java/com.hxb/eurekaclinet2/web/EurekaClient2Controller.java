package com.hxb.eurekaclinet2.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-02 16:50
 */
@RestController(value = "/api/eurekaClientHttp")
public class EurekaClient2Controller {

    @Value("${server.port}")
    private int port;

    @GetMapping(value = "/index")
    public String index () {
        return "Hello world ! 端口：" + this.port;
    }
}
