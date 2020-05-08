package com.hxb.webflux.web;

import com.hxb.webflux.service.impl.SpringBeanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-06-17 10:30
 */
@RestController
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SpringBeanController {

    @Autowired
    private SpringBeanServiceImpl springBeanServiceImpl;

    @GetMapping(value = "/showClassInfo")
    public void showClassInfo () {
        System.out.println("showClassInfo................");
        springBeanServiceImpl.setId("001");
        springBeanServiceImpl.setName("小明");
        springBeanServiceImpl.showClassInfo();
    }

    @GetMapping(value = "/showClassInfo1")
    public void showClassInfo1 () {
        System.out.println("showClassInfo1...........");
        springBeanServiceImpl.showClassInfo();
    }
}
