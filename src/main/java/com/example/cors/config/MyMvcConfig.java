package com.example.cors.config;

import com.example.cors.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册拦截器
 *
 * @author win10
 */
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMyInterceptor())
                .addPathPatterns("/**")
                // 不进行拦截的请求uri
                .excludePathPatterns("/login", "loginOut");
    }

    @Bean
    public MyInterceptor getMyInterceptor() {
        //设置哪些些地址不进行拦截
        List<String> allowPaths = new ArrayList<>();
        //allowPaths.add("http://localhost:8081/download/");
        allowPaths.add("http://127.0.0.1:8080");
        allowPaths.add("http://localhost:8080");
        allowPaths.add("http://121.12.87.207:8080");
        allowPaths.add("http://10.10.38.130:8080");
        allowPaths.add("http://172.31.139.40:8080");
        allowPaths.add("http://172.31.141.121:8080");
        allowPaths.add("http://172.31.134.112:8080");
        allowPaths.add("http://172.31.128.52:8080");
        return new MyInterceptor(allowPaths);
    }
}
