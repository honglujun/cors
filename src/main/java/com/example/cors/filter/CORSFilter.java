package com.example.cors.filter;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域请求过滤器
 *
 * @author win10
 */
@Component
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入过滤器CORSFilter。。。");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // 跨域过滤
        // 获取请求头中是否有要做跨域处理的请求url
        String origin = req.getHeader("Origin");
        // 携带cookies
        res.addHeader("Access-Control-Allow-Credentials", "true");
        // 请求方法类型
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        // 跨域的请求url
        res.setHeader("Access-Control-Allow-Origin", origin);
        // 表明服务器支持的所有头信息字段
        res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        // 过滤器的级别
        res.setHeader("XDomainRequestAllowed", "1");
        // 请求方法类型为预请求  通过
        if ("OPTIONS".equals(req.getMethod())) {
            response.getWriter().println("ok");
            return;
        }
        filterChain.doFilter(request, response);
        return;
    }

    @Override
    public void destroy() {

    }
}
