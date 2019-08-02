package com.vincent.example.threadLocal;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        httpServletRequest.getSession().getAttribute("user");
        log.info("do filter , {}, {}", Thread.currentThread().getId(), ((HttpServletRequest) request).getServletPath());
        RequestHolder.add(Thread.currentThread().getId());
        // 如果这个Filter不想拦截住这个请求，只想做单独的数据处理时，要调用chain.doFilter,使得拦截器处理完
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
