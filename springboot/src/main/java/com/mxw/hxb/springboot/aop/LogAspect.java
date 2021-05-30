package com.mxw.hxb.springboot.aop;

import com.mxw.hxb.springboot.annotations.LogAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 * 日志切面
 *
 * @author Ma_wei
 * @since 2019-09-05 14:38
 */
@Aspect
@Component
public class LogAspect {

    /** 日志*/
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    private static final String ENTER = "进入";

    private static final String METHOD = "()方法";

    private static final String COMMA = ".";

    private static final String COMMA1 = "...";

    private static final String COMMA2 = ">>>>>>";

    private static final String COMM3 = "<<<<<<";

    private static final String EXIT = "退出";



    /**
     * 切入点
     *
     * @author Ma_wei
     * @since 2019/9/5
     * @see Aspect
     * {@link Pointcut}
     */
    @Pointcut("@annotation(com.mxw.hxb.springboot.annotations.LogAnnotation)")
    public void pointcut() {

    }

    /**
     * Advice处理业务前的通知
     * order（1）
     *
     * @param joinPoint
     * @author Ma_wei
     * @since 2019/9/5
     * @see Aspect
     * {@link After}
     */
    @Before("pointcut() && @annotation(logAnnotation)")
    public void doBefore(JoinPoint joinPoint, LogAnnotation logAnnotation) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String simpleClassName = signature.getDeclaringType().getSimpleName();
        String className = signature.getDeclaringTypeName();
        String modifierType = Modifier.toString(signature.getModifiers());
        Object targetClass = joinPoint.getTarget();
        Object thisClass = joinPoint.getThis();
        log.info("获取到方法名为:{}",methodName);
        log.info("获取到方法所属简单类名为：{}",simpleClassName);
        log.info("获取方法所属类的类名：{}",className);
        log.info("目标方法声明类型:{}", modifierType);
        log.info("被代理的对象：{}",targetClass);
        log.info("代理对象自己：{}",thisClass);
        StringBuilder enterLog = new StringBuilder();
        enterLog.append(COMMA2).append(ENTER).append(simpleClassName).append(COMMA).append(methodName).append(METHOD).append(COMMA1);
        String logStr = enterLog.toString();
        log.info(logStr);
        try {
            /*// 获取类名
            Class clazz = joinPoint.getClass();
            log.info("获取到方法名为:{}",signature.getName());
            log.info("获取到方法所属简单类名为：{}",signature.getDeclaringType().getSimpleName());
            log.info("获取方法所属类的类名：{}",signature.getDeclaringTypeName());
            log.info("目标方法声明类型:{}", Modifier.toString(signature.getModifiers()));
            log.info("被代理的对象：{}",joinPoint.getTarget());
            log.info("代理对象自己：{}",joinPoint.getThis());*/
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        //joinPoint.getThis().getClass().get
    }

    /**
     * 业务代码处理后还未返回时的通知
     * order（2）
     *
     * @param joinPoint
     * @author Ma_wei
     * @since 2019/9/5
     * @see Aspect
     * {@link After}
     */
    @After("pointcut() && @annotation(logAnnotation)")
    public void doAfter(JoinPoint joinPoint,LogAnnotation logAnnotation) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String simpleClassName = signature.getDeclaringType().getSimpleName();
        /*String className = signature.getDeclaringTypeName();
        String modifierType = Modifier.toString(signature.getModifiers());
        Object targetClass = joinPoint.getTarget();
        Object thisClass = joinPoint.getThis();
        log.info("获取到方法名为:{}",methodName);
        log.info("获取到方法所属简单类名为：{}",simpleClassName);
        log.info("获取方法所属类的类名：{}",className);
        log.info("目标方法声明类型:{}", modifierType);
        log.info("被代理的对象：{}",targetClass);
        log.info("代理对象自己：{}",thisClass);*/
        StringBuilder exitLog = new StringBuilder();
        exitLog.append(simpleClassName).append(COMMA).append(methodName).append(METHOD).append(COMMA1).append(EXIT).append(COMM3);
        String value = logAnnotation.value();
        log.info(value);
        String logStr = exitLog.toString();
        log.info(logStr);
        try {
            /*// 获取类名
            Class clazz = joinPoint.getClass();
            log.info("获取到方法名为:{}",signature.getName());
            log.info("获取到方法所属简单类名为：{}",signature.getDeclaringType().getSimpleName());
            log.info("获取方法所属类的类名：{}",signature.getDeclaringTypeName());
            log.info("目标方法声明类型:{}", Modifier.toString(signature.getModifiers()));
            log.info("被代理的对象：{}",joinPoint.getTarget());
            log.info("代理对象自己：{}",joinPoint.getThis());*/
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Advice 通知 业务代码处理后得到返回结果时的通知
     * 比After执行晚  AfterThrowing 同级；  AfterThrowing 与  doAfterReturn 只能执行其中一个
     * order（3）
     *
     * @param joinPoint
     * @param object
     * @author Ma_wei
     * @since 2019/9/5
     */
    @AfterReturning(returning = "object",pointcut = "pointcut()")
    public void doAfterReturn(JoinPoint joinPoint,Object object) {
        log.debug("--业务代码处理后得到返回结果时通知--doAfterReturn");
    }

    /**
     * Advice通知 业务代码处理后得到返回结果时的通知
     * 比After执行晚  AfterReturning 同级；  AfterThrowing 与  doAfterReturn 只能执行其中一个
     *
     * @param joinPoint
     * @author Ma_wei
     * @since 2019/9/5
     */
    @AfterThrowing(value = "pointcut()")
    public void doAfterThrowing(JoinPoint joinPoint) {
        log.debug("--业务代码处理后得到返回结果时通知--doAfterThrowing--");
    }
}
