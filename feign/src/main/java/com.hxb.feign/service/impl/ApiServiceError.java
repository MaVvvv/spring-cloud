package com.hxb.feign.service.impl;

import com.hxb.feign.service.ApiService;
import org.springframework.stereotype.Component;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-03 16:13
 */
@Component
public class ApiServiceError implements ApiService {
    @Override
    public String index() {
        return "服务发生故障！";
    }
}
