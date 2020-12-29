package com.mxw.hxb.springboot.config;

import com.mxw.hxb.springboot.filter.TestFilter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * 过滤器注册
 *
 * @author Ma_wei
 * @since 2020/12/22 11:41
 */
//@SpringBootConfiguration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TestFilter());
        registration.addUrlPatterns("/*");
        registration.setName("TestFilter");
        registration.setOrder(6);
        return registration;
    }
}
