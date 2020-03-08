package com.thundersdata.backend.gateway.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {

    // 拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(rateLimiterInterceptor).addPathPatterns("/**");
        // registry.addInterceptor(authInterceptor).addPathPatterns("/rest/getUsers", "/rest/getUsersPage");
    }

    // 跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*");
    }
}
