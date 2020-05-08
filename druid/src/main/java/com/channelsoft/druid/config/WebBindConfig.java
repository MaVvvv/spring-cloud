package com.channelsoft.druid.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-03-26 17:27
 */
@SpringBootConfiguration
public class WebBindConfig {

    @Autowired
    public WebBindConfig(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        requestMappingHandlerAdapter.setWebBindingInitializer(new WebBindingInitializer());
    }

}
