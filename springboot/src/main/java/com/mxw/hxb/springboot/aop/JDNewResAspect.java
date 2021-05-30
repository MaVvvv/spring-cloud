package com.mxw.hxb.springboot.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;

/**
 * 新京东响应参数aop
 *
 * @author Ma_wei
 * @since 2021/3/5 14:12
 */
@Slf4j
@Aspect
@Component
public class JDNewResAspect {

    /**
     * 切入点
     *
     * @author Ma_wei
     * @since 2019/9/5
     * @see Aspect
     * {@link Pointcut}
     */
    @Pointcut(value = "execution(public void com.mxw.hxb.springboot.service.impl.JDNewService.getSkuInfo(java.lang.String))")
    public void pointcut() {}

    /**
     * Advice处理业务前的通知
     * order（1）
     *
     * @author Ma_wei
     * @since 2019/9/5
     * @see Aspect
     * {@link After}
     */
    /*@Before(value = "pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        if (Objects.isNull(args)) {
            log.warn("NULLLLLLLLLLLLLLLLLLLLLLLLLLL");
        } else {
            log.info(JSONObject.toJSONString(args[0]));
            Object arg1 = args[0];
            if (arg1 instanceof Method) {
                Method method = (Method) arg1;
                JDNewRes annotation = method.getAnnotation(JDNewRes.class);
                if (Objects.nonNull(annotation)) {
                    *//*JDNewResKeyEnum key = annotation.key();
                    log.info("key = {}",key);
                    // 设置方法返回值

                    switch (key) {
                        case SKU_IMG:

                    }*//*

                    Class<?> returnType = method.getReturnType();
                    AnnotatedType annotatedReturnType = method.getAnnotatedReturnType();
                    log.info("method.getDefaultValue() = {}",method.getDefaultValue());

                    Field[] declaredFields = returnType.getClass().getDeclaredFields();
                }

            }
        }
    }*/

    /**
     * Advice处理业务前的通知
     * order（1）
     *
     * @author Ma_wei
     * @since 2019/9/5
     * @see Aspect
     * {@link After}
     */
    //@Before(value = "pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        if (Objects.isNull(args)) {
            log.warn("NULLLLLLLLLLLLLLLLLLLLLLLLLLL");
        } else {
            log.info(JSONObject.toJSONString(args[0]));
            Object arg1 = args[0];
            log.info("arg1 = {}",arg1);
            // JSONObject
            JSONObject jsonObject = JSONObject.parseObject(arg1.toString());
            log.info("jsoObject = {}",jsonObject);
            if (jsonObject.containsKey("resResultInfo_sku")) {
                Object resResultInfo_sku = jsonObject.get("resResultInfo_sku");
                log.info("jsonObject.get(\"resResultInfo_sku\") = {}",resResultInfo_sku);
                jsonObject.remove("resResultInfo_sku");
                jsonObject.put("resResultInfo",JSONObject.toJSON(resResultInfo_sku));
            }
            log.info("new jsonObject = {}",jsonObject);
            String newArgs1 = JSONObject.toJSONString(jsonObject);
            log.info("newArgs1 = {}",newArgs1);
            args[0] = newArgs1;
        }
    }

    @Around(value = "pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            log.info(parameter.getName());
        }
        Object[] args = joinPoint.getArgs();
        Object arg1 = args[0];
        JSONObject jsonObject = JSONObject.parseObject(arg1.toString());
        log.info("jsoObject = {}",jsonObject);
        if (jsonObject.containsKey("resResultInfo_sku")) {
            Object resResultInfo_sku = jsonObject.get("resResultInfo_sku");
            log.info("jsonObject.get(\"resResultInfo_sku\") = {}",resResultInfo_sku);
            jsonObject.remove("resResultInfo_sku");
            jsonObject.put("resResultInfo",JSONObject.toJSON(resResultInfo_sku));
        } else if (jsonObject.containsKey("resResultInfo_sku111111")) {
            Object resResultInfo_sku = jsonObject.get("resResultInfo_sku111111");
            log.info("jsonObject.get(\"resResultInfo_sku\") = {}",resResultInfo_sku);
            jsonObject.remove("resResultInfo_sku111111");
            jsonObject.put("resResultInfo",JSONObject.toJSON(resResultInfo_sku));
        }
        args[0] = JSONObject.toJSONString(jsonObject);
        return joinPoint.proceed(args);
    }
}
