package com.hxb.feign.service;

import com.hxb.feign.service.impl.ApiServiceError;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-03 15:42
 */
@FeignClient(value = "eurekaclient",fallback = ApiServiceError.class)
public interface ApiService {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    String index();

    @RequestMapping(value = "/index1",method = RequestMethod.GET)
    String index1();

}
