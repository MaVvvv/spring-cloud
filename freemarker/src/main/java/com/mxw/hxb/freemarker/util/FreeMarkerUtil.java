package com.mxw.hxb.freemarker.util;

import com.mxw.hxb.freemarker.hello.TemplateObj;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Freemarker工具类
 *
 * @author Ma_wei
 * @version 1.0
 * @className FreeMarkerUtil.class
 * @since 2021/4/16 10:18
 */
@Slf4j
@Component
public class FreeMarkerUtil {

    /**
     * Freemarker配置实例
     */
    @Autowired
    private Configuration freemarkerConfig;

    /**
     * 判断包路径是否存在
     * 
     * @method pathJudgeExist
     * @param path              文件地址
     * @author Ma_wei
     * @since 2021/4/16 11:17
     */
    private void pathJudgeExist(String path){
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 获取模板
     * 
     * @method getTemplate
     * @return Template     freemarker模板
     * @author Ma_wei
     * @since 2021/4/16 11:08
     */
    public Template getTemplate(String templateName) {
        try {
            return freemarkerConfig.getTemplate(templateName);
        } catch (IOException e) {
            log.error("FreeMarkerUtil.getTemplate exception",e);
        }
        return null;
    }


    /**
     * 写出代码文件
     * 
     * @method writeCodeFile
     * @param template          freemarker模板
     * @param fileName          代码生成文件名称
     * @param filePath          代码生成地址
     * @author Ma_wei
     * @since 2021/4/16 11:23
     */
    public void writeCodeFile(TemplateObj obj, Template template, String filePath, String fileName) throws Exception  {
        pathJudgeExist(filePath);
        File file = new File(filePath, fileName );
        if(!file.exists()) {
            file.createNewFile();
        }
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
        template.process(obj,  out);
        out.close();
    }

    /**
     * 生成代码
     * 
     * @method generate
     * @param obj           模板定义对象
     * @param templateName  模板名称
     * @param filePath      文件生成路径
     * @param fileName      文件名称
     * @author Ma_wei
     * @since 2021/4/16 11:15
     */
    public void generateCode(TemplateObj obj,String templateName,String filePath,String fileName) throws Exception {
        log.info("【generateCode】 templateName = {},filePath = {},fileName = {}",templateName,filePath,fileName);
        Template template = this.getTemplate(templateName);
        this.writeCodeFile(obj,template,filePath,fileName);
        log.info("【generateCode】 success!");
    }
}
