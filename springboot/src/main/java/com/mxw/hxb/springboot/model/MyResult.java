package com.mxw.hxb.springboot.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * 响应结果
 *
 * @author Ma_wei
 * @since 2020/12/28 16:48
 */
@Slf4j
public class MyResult {

    private final String code;

    private final String errorMsg;

    private String business;

    public MyResult(String code, String errorMsg) {
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        for (StackTraceElement stack : stacks) {
            log.info("{} ::::::: {} ::::::: {}",stack.getFileName(),stack.getClassName(),stack.getMethodName());
            String className = stack.getClassName();
            if (className.contains("com.mxw.hxb.springboot.web")) {
                this.business = "controller";
                break;
            } else if (className.contains("com.mxw.hxb.springboot.service.impl")) {
                this.business = "service";
                break;
            }
        }
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public String getCode() {
        return code;
    }

    public String getErrorMsg() {
        // 获取堆栈信息
        /*StackTraceElement[] stackTraceElement = Thread.getAllStackTraces().get(Thread.currentThread());
        for (StackTraceElement stack : stackTraceElement) {
            log.info("{} ::::::: {} ::::::: {}",stack.getFileName(),stack.getClassName(),stack.getMethodName());
        }*/
        log.info("this.business = {}",this.business);
        return errorMsg;
    }

    public String getBusiness() {
        return business;
    }
}
