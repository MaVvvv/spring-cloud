package com.mxw.hxb.springboot.annotations;

import com.sunyur.common.i18n.enums.SyI18nConfigTypeEnum;

import java.lang.annotation.*;

/**
 * 国际化字段属性标识
 *
 * @author Ma_wei
 * @since 2019-09-05 19:45
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SyI18nField {

    /**
     * 属性类型
     */
    SyI18nConfigTypeEnum type();
}
