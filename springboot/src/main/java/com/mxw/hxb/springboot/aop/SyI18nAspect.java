package com.mxw.hxb.springboot.aop;

import com.mxw.hxb.springboot.annotations.SyI18nApi;
import com.mxw.hxb.springboot.annotations.SyI18nField;
import com.mxw.hxb.springboot.annotations.SyI18nFieldRoot;
import com.sunyur.common.i18n.enums.SyI18nConfigTypeEnum;
import com.sunyur.common.i18n.enums.SyI18nLanguageEnum;
import com.sunyur.common.pagination.ModelListResult;
import com.sunyur.common.pagination.ModelResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

import java.lang.reflect.*;
import java.util.Collection;
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
    @AfterReturning(value = "pointcut() && @annotation(syI18nApi)",returning = "obj")
    public void doAfterReturning(Object obj, SyI18nApi syI18nApi) {
        try {
            if (obj instanceof ModelListResult) {
                ModelListResult<?> modelListResult = (ModelListResult<?>) obj;
                Object contentObjs = modelListResult.getContent();
                if (Objects.nonNull(contentObjs)) {
                    this.handleCollectionObj(contentObjs);
                }
            } else if (obj instanceof ModelResult) {
                ModelResult<?> modelResult = (ModelResult<?>) obj;
                Object contentObj = modelResult.getContent();
                if (Objects.nonNull(contentObj)) {
                    if (contentObj instanceof Collection) {
                        this.handleCollectionObj(contentObj);
                    } else {
                        Field[] declaredFields = contentObj.getClass().getDeclaredFields();
                        for (Field declaredField : declaredFields) {
                            this.handleObject(contentObj,declaredField);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 处理集合类型数据
     * 
     * @param contentObj    集合对象
     * @author Ma_wei
     * @since 2020/12/31
     */
    private void handleCollectionObj(Object contentObj){
        Collection objects = (Collection) contentObj;
        objects.forEach(object -> {
            Field[] objectDeclaredFields = object.getClass().getDeclaredFields();
            for (Field objectDeclaredField : objectDeclaredFields) {
                this.handleObject(object,objectDeclaredField);
            }
        });
    }

    /**
     * 处理对象
     *
     * @param object                父对象
     * @param objectDeclaredField   字段实例
     * @return
     * @author Ma_wei
     * @since 2020/12/31
     */
    private void handleObject(Object object,Field objectDeclaredField) {
        SyI18nField syI18nField = objectDeclaredField.getAnnotation(SyI18nField.class);
        if (Objects.nonNull(syI18nField)) {
            this.reWriterFieldValue(syI18nField.type(),object,objectDeclaredField);
        } else {
            SyI18nFieldRoot annotation = objectDeclaredField.getAnnotation(SyI18nFieldRoot.class);
            if (Objects.nonNull(annotation)) {
                objectDeclaredField.setAccessible(true);
                try {
                    Object defObject = objectDeclaredField.get(object);
                    if (defObject instanceof Collection) {
                        this.handleCollectionObj(defObject);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 判断和重写响应结果值
     *
     * @param contentObj    操作的vo对象实例
     * @param declaredField 对象字段对象
     * @author Ma_wei
     * @since 2020/12/24
     */
    private void reWriterFieldValue(SyI18nConfigTypeEnum configTypeEnum,Object contentObj, Field declaredField) {
        try {
            log.info("configTypeEnum = {}",configTypeEnum);
            declaredField.setAccessible(true);
            String resultValue = (String)declaredField.get(contentObj);
            // 国际化操作
            if (true){  //LangCookieUtil.checkHtmlLangCookieExists()
                String langName = Strings.EMPTY;
                SyI18nLanguageEnum syI18nLangEnum = SyI18nLanguageEnum.EN;//LangCookieUtil.readHtmlLangCookieValue();
                if (syI18nLangEnum != SyI18nLanguageEnum.CN) {
                    switch (configTypeEnum) {
                        case MENU:
                            langName = "Menu abc";//dubboQueryLangMsgService.getI18nLangMenuMsg(resultValue,syI18nLangEnum);
                            break;
                        case APPLICATION:
                            langName = "Application abc";//dubboQueryLangMsgService.getI18nLangApplicationMsg(resultValue,syI18nLangEnum);
                            break;
                        case APPLICATION_CLASSIFY:
                            langName = "Application_Classify abc";//dubboQueryLangMsgService.getI18nLangApplicationClassifyMsg(resultValue,syI18nLangEnum);
                            break;
                        default:
                            log.warn("not found SyI18nConfigTypeEnum from @interface SyI18nApi.class");
                    }
                    if (StringUtils.isNotBlank(langName)) {
                        // 赋值国际化翻译
                        declaredField.set(contentObj,langName);
                    }
                }
            }
        } catch (Exception e) {
            log.error("SyI18nAspect reWriterFieldValue exception FieldName = {}",declaredField.getName());
        }
    }
}
