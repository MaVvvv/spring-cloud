package com.mxw.hxb.springboot.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-09-05 19:45
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LogAnnotation {

    String value() default "";
}
