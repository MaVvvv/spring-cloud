package com.mxw.hxb.springboot.config.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Objects;

/**
 * 国际化springbean管理工具类
 *
 * @author Ma_wei
 * @since 2020/12/29 12:02
 */
public class I18nSpringBeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(Objects.isNull(com.mxw.hxb.springboot.config.bean.I18nSpringBeanUtils.applicationContext)){
            com.mxw.hxb.springboot.config.bean.I18nSpringBeanUtils.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 根据bean名称获取bean
     * @param name
     * @return
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * 根据bean class获取bean
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 根据bean名称和bean class获取bean
     * @param name
     * @param clazz
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
