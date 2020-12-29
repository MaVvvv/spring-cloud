package com.cool.service.impl;

import com.cool.service.ISpringIocService;
import org.springframework.stereotype.Service;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-04-09 19:56
 */
public class SpringIocServiceImpl implements ISpringIocService {

    @Override
    public String showIoc() {
        return "I'm Spring IOC";
    }
}
