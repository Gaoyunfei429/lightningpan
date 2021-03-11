package com.lightning.portal.config;

import com.lightning.portal.interceptor.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author gyf
 * @Date 2021-02-23 17:25
 * @ClassName AdminWebConfig
 * @Description
 */
//@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
