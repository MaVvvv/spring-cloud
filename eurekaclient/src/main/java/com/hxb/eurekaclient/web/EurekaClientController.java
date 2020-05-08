package com.hxb.eurekaclient.web;

import com.hxb.eurekaclient.api.Client2Api;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EurekaClientController {

    @Value("${server.port}")
    private int port;

    @Autowired
    private Client2Api client2Api;

    @GetMapping(value = "/index")
    public String index () {
        return " index Hello world ! 端口：" + this.port;
    }

    @GetMapping(value = "/index1")
    public String index1 () {
        return client2Api.index();
    }
}
