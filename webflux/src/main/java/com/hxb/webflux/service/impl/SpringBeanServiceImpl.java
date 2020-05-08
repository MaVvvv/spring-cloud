package com.hxb.webflux.service.impl;

import com.hxb.webflux.service.ISpringBeanService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-06-17 10:27
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SpringBeanServiceImpl implements ISpringBeanService {

    private String name;

    private String id;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public void showClassInfo() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "SpringBeanServiceImpl{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
