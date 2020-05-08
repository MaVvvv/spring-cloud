package com.mxw.hxb.springboot.web;

import com.mxw.hxb.springboot.annotations.LogAnnotation;
import com.mxw.hxb.springboot.service.IAopService;
import com.mxw.hxb.springboot.utils.AuthorizeIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-09-04 10:07
 */
@RestController
@ResponseBody
@RequestMapping(value = "/api")
public class SpringBootController {

    /** 日志 {@link Logger}*/
    private static final Logger log = LoggerFactory.getLogger(SpringBootController.class);

    @Autowired
    private IAopService aopServiceImpl;

    @GetMapping(value = "authorize")
    public String authorize(@Valid AuthorizeIn authorizeIn, BindingResult bindingResult) {
        authorizeIn.getRedirectUri();
        validate(bindingResult);
        return "authorize";
    }

    protected void validate(BindingResult result){
        if(result.hasFieldErrors()){
            List<FieldError> errorList = result.getFieldErrors();
            errorList.stream().forEach(item -> Assert.isTrue(false,item.getDefaultMessage()));
        }
    }

    @LogAnnotation(value = "测试AOP")
    @GetMapping(value = "/testAOP")
    public String testAOP(@RequestParam(value = "a") int a,@RequestParam(value = "b") int b) {
        return aopServiceImpl.test(a,b);
    }
}
