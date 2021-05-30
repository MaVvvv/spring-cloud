package com.mxw.hxb.freemarker.config;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Freemarker配置类
 *
 * @author Ma_wei
 * @version 1.0
 * @className FreeMarkerConfig.class
 * @since 2021/4/16 10:20
 */
@Slf4j
@SpringBootConfiguration
public class FreeMarkerConfig {

    /**
     * 代码生成根路径
     */
    @Value("${app.freemarker.coed-generation.root}")
    private String coedGenerationRootPath;

    /**
     * 模板根路径
     */
    @Value("${app.freemarker.templates.root}")
    private String templatesRootPath;

    /**
     * 加载freemarker配置类
     * 
     * @method buildFreemarkerConfiguration
     * @return Configuration
     * @author Ma_wei
     * @since 2021/4/16 11:00
     */
    @Bean
    public Configuration freemarkerConfig() throws IOException {
        log.info("freemarkerConfig...");
        Configuration freemarker = new Configuration(Configuration.VERSION_2_3_31);
        freemarker.setDirectoryForTemplateLoading(new File(coedGenerationRootPath + File.separator + templatesRootPath));
        freemarker.setDefaultEncoding(StandardCharsets.UTF_8.name());
        freemarker.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return freemarker;
    }
}
