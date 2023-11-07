package com.besp.likebesp1.config;

import org.springframework.beans.factory.annotation.Value;
import com.besp.likebesp1.common.interceptor.AuthenticatedUserInterceptor;
import com.besp.likebesp1.common.interceptor.GuestUserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@PropertySource("classpath:/application.yml")
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

    @Value("${fileUploadPath}")
    String  fileUploadPath;
    @Value("${domain}")
    String  domain;

    //파일 서버 생성 가능
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(registry);
    }

    void exposeDirectory(ResourceHandlerRegistry registry)
    {
        Path uploadDir = Paths.get(fileUploadPath);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        System.out.println("업로드 경로 : " + uploadPath);

        if(fileUploadPath.startsWith("../"))
        {
            fileUploadPath = fileUploadPath.replace("../", "");
        }

        System.out.println("업로드 상대적 경로 : " + fileUploadPath);
        registry.addResourceHandler("/" + fileUploadPath + "/**")
                .addResourceLocations("file:/" + uploadPath + "/");
    }
}
