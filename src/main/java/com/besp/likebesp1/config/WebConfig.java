package com.besp.likebesp1.config;

import com.besp.likebesp1.common.interceptor.AuthenticatedUserInterceptor;
import com.besp.likebesp1.common.interceptor.GuestUserInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticatedUserInterceptor())
                .order(0)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/error", "/member/login", "/member/register", "/assets/**", "/member/logout");

        registry.addInterceptor(new GuestUserInterceptor())
                .order(1)
                .addPathPatterns("/member/login", "/member/register");

    }
}
