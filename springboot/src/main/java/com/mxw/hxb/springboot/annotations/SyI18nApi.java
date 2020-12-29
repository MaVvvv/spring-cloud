package com.mxw.hxb.springboot.annotations;

import java.lang.annotation.*;

/**
 * 国际化接口标识
 *
 * @author Ma_wei
 * @since 2019-09-05 19:45
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SyI18nApi {

    String value() default "";
}
