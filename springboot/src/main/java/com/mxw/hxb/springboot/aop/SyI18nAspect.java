package com.mxw.hxb.springboot.aop;

import com.mxw.hxb.springboot.annotations.SyI18nField;
import com.mxw.hxb.springboot.model.OrderModel;
import com.sunyur.common.pagination.ModelResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * 日志切面
 *
 * @author Ma_wei
 * @since 2019-09-05 14:38
 */
@Slf4j
@Aspect
@Component
public class SyI18nAspect {

    /**
     * 切入点
     *
     * @author Ma_wei
     * @since 2019/9/5
     * @see Aspect
     * {@link Pointcut}
     */
    @Pointcut(value = "@annotation(com.mxw.hxb.springboot.annotations.SyI18nApi)")
    public void pointcut() {}

    /**
     * Advice处理业务前的通知
     * order（1）
     *
     * @param obj
     * @author Ma_wei
     * @since 2019/9/5
     * @see Aspect
     * {@link After}
     */
    @AfterReturning(value = "pointcut()",returning = "obj")
    public void doAfterReturning(Object obj) {
        try {
            ModelResult<?> modelResult = (ModelResult<?>) obj;
            Object contentObj = modelResult.getContent();
            Field[] declaredFields = contentObj.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                Annotation annotation = declaredField.getAnnotation(SyI18nField.class);
                if (Objects.nonNull(annotation)) {
                    String name = declaredField.getName();
                    declaredField.setAccessible(true);
                    String resultValue = (String)declaredField.get(contentObj);
                    log.info("FiledName = {} FiledValue = {}",name,resultValue);

                    declaredField.set(contentObj,"abc");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
