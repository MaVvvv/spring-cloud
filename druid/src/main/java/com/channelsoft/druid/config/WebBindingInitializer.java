package com.channelsoft.druid.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-03-27 11:49
 */
public class WebBindingInitializer extends ConfigurableWebBindingInitializer {

    @Override
    public void initBinder(WebDataBinder binder, WebRequest webRequest) {
        binder.setAutoGrowCollectionLimit(1);
    }
}
