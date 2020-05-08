package com.hxb.eurekaclinet2.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-02 16:50
 */
@RestController
@RequestMapping("/api/eurekaClientHttp")
public class EurekaClient2Controller {

    private static final Logger log = LoggerFactory.getLogger(EurekaClient2Controller.class);

    @Value("${server.port}")
    private int port;

    @GetMapping(value = "/index")
    public String index () {
        log.info("调用EurekaClient2Controller.index1()方法...");
        return "Hello world !EurekaClient2 端口：" + this.port;
    }
}
