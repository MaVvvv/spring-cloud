package com.mxw.hxb.freemarker.hello.clas;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author Ma_wei
 * @version 1.0
 * @className FreemarkerDemo.class
 * @since 2021/4/14 10:38
 */
public class FreemarkerDemo {

    private static final String TEMPLATE_PATH = "src/main/java/com/mxw/hxb/freemarker/hello/templates";
    private static final String CLASS_PATH = "src/main/java/com/mxw/hxb/freemarker/hello";

    public static void main(String[] args) {
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            TemplateLoader templateLoader = new FileTemplateLoader(new File("E:\\workspace_user\\spring-cloud\\freemarker"));

            // step2 获取模板路径
            configuration.setTemplateLoader(templateLoader);
            // step3 创建数据模型
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("classPath","com.mxw.hxb.freemarker.hello.clas");
            dataMap.put("className","AutoCodeDemo");
            dataMap.put("helloWorld", "通过简单的 <代码自动生产程序> 演示 FreeMarker的HelloWorld！");

            // step4 加载模板文件
            Template template = configuration.getTemplate("com/mxw/hxb/freemarker/hello/templates/hello.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + "AutoCodeDemo.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));

            template.process(dataMap, out);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^AutoCodeDemo.java 文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
