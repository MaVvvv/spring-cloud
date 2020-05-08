package com.hxb.eurekaclient.api;

import com.hxb.eurekaclient.config.bean.Client1FeignConfig;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-11-19 10:18
 */
@FeignClient(name = "eurekaclient2",configuration = Client1FeignConfig.class)
public interface Client2Api {

    //@GetMapping(value = "/api/eurekaClientHttp/index")
    @RequestLine("GET /api/eurekaClientHttp/index")
    String index ();
}
