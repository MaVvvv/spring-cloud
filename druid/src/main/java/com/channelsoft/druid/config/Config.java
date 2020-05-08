package com.channelsoft.druid.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-06-05 20:35
 */
@SpringBootConfiguration
public class Config {

    @Bean
    public ServletRegistrationBean druidStatViewServle () {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        // 添加初始化参数
        // 白名单
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        // IP黑名单（存在共同时，黑名单优先于白名单 deny > allow）
        servletRegistrationBean.addInitParameter("deny","192.168.1.73");
        // 登陆查看信息账号密码
        servletRegistrationBean.addInitParameter("LoginUsername","admin");
        servletRegistrationBean.addInitParameter("LoginPassword","123456");
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter () {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        //　添加忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.css");
        return filterRegistrationBean;
    }
}
