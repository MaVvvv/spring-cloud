package com.mxw.hxb.freemarker.hello;

import lombok.Data;


/**
 * Freemarker模板定义Object类
 *
 * @author Ma_wei
 * @version 1.0
 * @className FreemakerTemplateObj.class
 * @since 2021/4/16 11:26
 */
@Data
public abstract class TemplateObj {

    private String classPath;

    private String className;

    private String listPackage;
}
