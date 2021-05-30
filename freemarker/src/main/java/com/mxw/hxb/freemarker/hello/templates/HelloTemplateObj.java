package com.mxw.hxb.freemarker.hello.templates;

import com.mxw.hxb.freemarker.hello.TemplateObj;
import com.mxw.hxb.freemarker.model.ParamDefinition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * TODO
 *
 * @author Ma_wei
 * @version 1.0
 * @className HelloTemplateObj.class
 * @since 2021/4/23 16:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HelloTemplateObj extends TemplateObj {

    /**
     * 内容
     */
    private String content;

    /**
     * 参数定义
     */
    private ParamDefinition inition;

    private List<String> ids;
}
