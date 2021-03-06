package com.cool.service.impl;

import com.cool.service.ISpringIocService;
import org.springframework.stereotype.Service;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-04-10 17:56
 */
@Service
public class SpringIocServiceAnImpl implements ISpringIocService {

    @Override
    public String showIoc() {
        return "I'm Spring 注解 Ioc";
    }
}
