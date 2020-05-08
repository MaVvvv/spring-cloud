package com.hxb.jdk8.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-01-07 11:15
 */
@Controller
@RequestMapping("/api/jdk8")
public class Jdk8Controller {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(Jdk8Controller.class);

    @GetMapping(value = "/mycat")
    public String testMyCat() {
        log.debug("开始测试myCat...");
        return null;
    }
}
