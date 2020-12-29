package com.mxw.hxb.springboot.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 国际化应用配置
 *
 * @author Ma_wei
 * @since 2020/12/29 10:54
 */
public class I18nApplicationMap {

    /**
     * 从配置文件中读取的limitSizeMap开头的数据
     * 注意：名称必须与配置文件中保持一致
     */
    private Map<String, Map<String,List<String>>> applicationMap = new HashMap<>();

    public Map<String, Map<String, List<String>>> getApplicationMap() {
        return applicationMap;
    }

    public void setApplicationMap(Map<String, Map<String, List<String>>> applicationMap) {
        this.applicationMap = applicationMap;
    }
}
