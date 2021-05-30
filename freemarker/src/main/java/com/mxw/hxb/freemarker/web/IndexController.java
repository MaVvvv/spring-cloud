package com.mxw.hxb.freemarker.web;

import com.mxw.hxb.freemarker.hello.templates.HelloTemplateObj;
import com.mxw.hxb.freemarker.model.ParamDefinition;
import com.mxw.hxb.freemarker.util.FreeMarkerUtil;
import com.mxw.hxb.freemarker.util.JsonToParamDefinitionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * TODO
 *
 * @author Ma_wei
 * @version 1.0
 * @className IndexController.class
 * @since 2021/4/14 11:45
 */
@RequestMapping(value = "/index")
@RestController
public class IndexController {

    @Autowired
    private FreeMarkerUtil freeMarkerUtil;

    @GetMapping
    public String index() throws Exception {
        HelloTemplateObj templateObj = new HelloTemplateObj();
        templateObj.setClassPath("com.mxw.hxb.freemarker.hello");
        templateObj.setClassName("HelloDemo");
        templateObj.setContent("Hello world!");
        templateObj.setIds(Arrays.asList("a","b","c","d"));

        freeMarkerUtil.generateCode(templateObj,"hello.ftl","E:\\workspace_user\\spring-cloud\\freemarker\\src\\main\\java\\com\\mxw\\hxb\\freemarker\\hello\\clas","HelloDemo.java");
        return "index";
    }

    @GetMapping(value = "/model")
    public String indexModel() throws Exception {
        HelloTemplateObj templateObj = new HelloTemplateObj();
        templateObj.setClassPath("com.mxw.hxb.freemarker.hello");
        templateObj.setClassName("HelloModel");
        templateObj.setContent("Hello world!");

        ParamDefinition convert = JsonToParamDefinitionUtil.convert("{\"name\":\"小明\",\"age\":15,\"sex\":true,\"nos\":[{\"id\":1,\"na\":\"zhang\"},{\"id\":2,\"na\":\"zhang2\"}],\"info\":{\"chinese\":98,\"english\":66,\"math\":100,\"total\":\"良好\"}}");
        convert.getSubParams().stream().forEach(sub -> {
            if (!sub.getType().equals("boolean")) {
                sub.setDescription("未定义");
            }
        });
        //ParamDefinition p1 = new ParamDefinition("root",true);
        templateObj.setInition(convert);
        freeMarkerUtil.generateCode(templateObj,"a/helloModel.ftl","E:\\workspace_user\\spring-cloud\\freemarker\\src\\main\\java\\com\\mxw\\hxb\\freemarker\\hello\\model","HelloModel.java");
        return "index";
    }
}
