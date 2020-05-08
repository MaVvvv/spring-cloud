package com.hxb.eurekaclient.config.bean;

import feign.Contract;
import feign.Logger;
import feign.Request;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-11-19 11:12
 */
@SpringBootConfiguration
public class Client1FeignConfig {

    /**
     * 使用feign默认的契约，而不是是使用spring mvc的契约
     * 注意：此时Client1FeignConfig类上的方法的写法发生了改变
     *
     * @param
     * @return 
     * @author Ma_wei
     * @since 2019/11/19
     */
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    /**
     * 修改日志的级别，其余的级别看Logger.level这个枚举类
     * 全出feign输出
     * @see Logger.Level
     * @param
     * @return 
     * @author Ma_wei
     * @since 2019/11/19
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 设置请求的连接和处理的超时时间
     *
     * @param
     * @return 
     * @author Ma_wei
     * @since 2019/11/19
     */
    @Bean
    public Request.Options feignRequestOptions() {
        return new Request.Options(10000,60000);
    }

}
