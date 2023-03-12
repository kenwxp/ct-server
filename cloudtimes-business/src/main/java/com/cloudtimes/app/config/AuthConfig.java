package com.cloudtimes.app.config;

import com.cloudtimes.app.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
                //拦截
                .addPathPatterns("/**")
                //放行
                .excludePathPatterns(
                        "/swagger-ui.html",
                        "/auth/device/test",
                        "/auth/device/login",
                        "/auth/user/**",
                        "/mapp/login/check",
                        "/mapp/login",
                        "/mobile/login/register",
                        "/mobile/login",
                        "/cash/login/check",
                        "/cash/login",
                        "/door/login/check",
                        "/door/login",
                        "/auth/shopBoss/register",
                        "/auth/shopBoss/login",
                        "/system/**",
                        "/test/**",
                        "/sms/**",
                        "/mp/wx_auth",
                        "/mp/user/h5_login",
                        "/mp/user/register",
                        "/mp/store/register",
                        "/rcyg/**",
                        "/mp/**"
                )
                .excludePathPatterns("/js/**", "/css/**", "/images/**", "/lib/**",
                        "/fonts/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui/**", "/v3/**", "/error", "/**.ico", "/doc**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(false)
                //放行哪些原始域
                .allowedOrigins("*")
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedHeaders("*")
                .exposedHeaders("*");
    }

}
