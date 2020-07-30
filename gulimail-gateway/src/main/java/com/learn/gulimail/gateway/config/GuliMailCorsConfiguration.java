package com.learn.gulimail.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @ClassName GuliCorsConfiguration
 * @Description TODO
 * @Author chenfuyuan
 * @Date 2020-7-30 20:16
 * @Version 1.0
 */
@Configuration
public class GuliMailCorsConfiguration {

    /**
     * 配置过滤器，解决跨域问题
     * @return 返回CorsWebFilter过滤器
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        //ConfigurationSource的实现类 UrlBaseCorsConfguration
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //Cors 跨域资源共享"（Cross-origin resource sharing）
        //配置跨域共享信息，用以处理跨域问题
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        //Access-Control-Allow-Headers 表明服务器支持的所有头信息字段 *，表示接受任意头信息字段
        corsConfiguration.addAllowedHeader("*");
        //Access-Control-Allow-Methods 表明服务器支持的所有跨域请求的方法。 *，表示接受任何方法
        corsConfiguration.addAllowedMethod("*");
        //Access-Control-Allow-Origin 表明服务器支持哪些来源的请求跨域 *，表示接受任意域名的请求
        corsConfiguration.addAllowedOrigin("*");
        //Access-Control-Allow-Credentials 表示是否允许发送Cookie.
        corsConfiguration.setAllowCredentials(true);
        //   "/**" 设置Path 表示任意路径都需要进行跨域配置
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }
}
