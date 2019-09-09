package com.example.cors.interceptor;

import com.example.cors.domain.LoginUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 判断是否登录的拦截器
 *
 * @author win10
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

    private List<String> allowPaths;
    // 定义一个线程域，存放登录用户
    private static final ThreadLocal<LoginUserInfo> tl = new ThreadLocal<>();

    public MyInterceptor(List<String> allowPaths) {
        this.allowPaths = allowPaths;
    }

    /**
     * This implementation always returns {@code true}.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("进入过滤器CORSFilter。。。");
        // 获取请求头中是否有要做跨域处理的请求url
        String origin = request.getHeader("Origin");
        // 也可以做跨域配置
        // 设置允许哪些域名应用进行ajax访问
        // response.setHeader("Access-Control-Allow-Origin", "*");//个人感觉下面的方式更合理一些
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // 可以做登录校验
        String url = request.getRequestURL().toString();
        // 放行过滤的url
        for (String allowPath : allowPaths) {
            if (StringUtils.contains(url, allowPath)) {
                return true;
            }
        }
        return true;
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {


    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response,
                                               Object handler) throws Exception {
    }

}