package com.mxw.hxb.springboot.annotations;

import com.mxw.hxb.springboot.enums.JDNewResKeyEnum;

import java.lang.annotation.*;

/**
 * 国际化接口标识
 *
 * @author Ma_wei
 * @since 2019-09-05 19:45
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JDNewRes {

}
