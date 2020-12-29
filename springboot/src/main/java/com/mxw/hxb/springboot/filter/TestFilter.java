package com.mxw.hxb.springboot.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 测试过滤器
 *
 * @author Ma_wei
 * @since 2020/12/22 11:36
 */
@Slf4j
public class TestFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("testFilter.init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("testFilter.doFilter()");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String contextPath = request.getContextPath();
        log.info("contextPath = {}",contextPath);
        Cookie[] cookies = request.getCookies();
        if (Objects.nonNull(cookies)) {
            for (Cookie cookie:cookies) {
                log.info("cookie.domain = {}",cookie.getDomain());
                log.info(cookie.toString());
            }
        }
        SessionCookieConfig sessionCookieConfig = servletRequest.getServletContext().getSessionCookieConfig();
        log.info("name = {}",sessionCookieConfig.getName());
        log.info("domain = {}",sessionCookieConfig.getDomain());
        log.info("path = {}",sessionCookieConfig.getPath());
        log.info("comment = {}",sessionCookieConfig.getComment());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("testFilter.destroy()");
    }
}
