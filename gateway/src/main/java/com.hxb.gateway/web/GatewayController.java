package com.hxb.gateway.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-03 10:33
 */
@Slf4j
@RestController
@RequestMapping(value = "/gateWay")
public class GatewayController {

    @ResponseBody
    @PostMapping(value = "/test/urlencoded")
    public String testContentTypeUrlencoded(@RequestHeader("Content-Type") String contentType,@RequestBody String params,@RequestParam String tag){
        log.info("tag = {}",tag);
        log.info("contentType = {}",contentType);
        log.info("params = {}",params);
        return params;
    }

    @ResponseBody
    @PostMapping(value = "/test/urlencoded1")
    public String testContentTypeUrlencoded1(@RequestHeader(value = "Content-Type",required = false) String contentType,
                                             @RequestBody String requestInfo, @RequestParam(required = false) String tag,
                                             @RequestParam(required = false) String businessId){
        log.info("tag = {}",tag);
        log.info("contentType = {}",contentType);
        log.info("params = {}",requestInfo);
        return businessId;
    }

    @ResponseBody
    @PostMapping(value = "/test/json")
    public String testContentTypeJson(@RequestHeader("Content-Type") String contentType, @RequestBody String params){
        log.info("contentType = {}",contentType);
        log.info("contentType is JSON = {}", contentType.equals(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        log.info("params = {}",params);
        return params;
    }
}
