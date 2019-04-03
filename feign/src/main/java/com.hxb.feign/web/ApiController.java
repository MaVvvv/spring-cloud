package com.hxb.feign.web;

import com.hxb.feign.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-03 16:15
 */
@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @RequestMapping("/index")
    public String index () {
        return apiService.index();
    }
}
