package com.learn.project.mall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * 配置跨域问题
 *
 * @author chenfuyuan
 * @date 2021/12/11 15:21
 */
@Configuration
public class GlobalCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        //请求头
        configuration.addAllowedHeader("*");
        //方法
        configuration.addAllowedMethod("*");
        //源
        //configuration.addAllowedOrigin("*"); 不能包含特殊值*,它不能在Access-Control-Allow-Origin被设置。应该换成addAllowedOriginPattern("*")
        //configuration.addAllowedOrigin("*");
        configuration.addAllowedOriginPattern("*");
        //cookie
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsWebFilter(source);
    }
}
