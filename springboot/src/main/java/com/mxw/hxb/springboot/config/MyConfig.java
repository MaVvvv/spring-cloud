package com.mxw.hxb.springboot.config;

import com.mxw.hxb.springboot.config.bean.I18nSpringBeanUtils;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 我的配置
 *
 * @author Ma_wei
 * @since 2020/12/29 11:21
 */
//@SpringBootConfiguration
public class MyConfig {

    @Bean
    @ConfigurationProperties(prefix = "model-result")
    public I18nApplicationMap buildI18nApplicationMap(){
        return new I18nApplicationMap();
    }

    @Bean
    public I18nSpringBeanUtils buildI18nSpringBeanUtils() {
        return new I18nSpringBeanUtils();
    }
}
