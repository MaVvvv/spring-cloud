package com.hxb.service;

import com.hxb.web.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-09-10 20:06
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DogService {

    @Autowired
    private Dog dog;

    public void showToString() {
        System.out.println(dog.toString());
    }

    public void setDogInfo(String name,String age) {
        dog.setName(name);
        dog.setAge(age);
    }

    public void setDogInfo2(String name,int size) {
        dog.setName(name);
        dog.setSize(size);
    }
}
