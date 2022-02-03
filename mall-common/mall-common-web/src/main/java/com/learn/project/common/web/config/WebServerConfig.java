package com.learn.project.common.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.project.common.web.util.jsonSerializer.DateFormatterAnnotationIntrospector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.nio.charset.StandardCharsets;
import java.util.TimeZone;

/**
 * Web设置
 * @author chenfuyuan
 * @date 2022/1/21 19:08
 */
@Configuration
public class WebServerConfig {

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        // Config the Json convert Chinese garbled.
        // 这里的配置可能会导致application.properties文件中spring.jackson.time-zone=GMT+8失效
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        // 不设置Utf-8格式,可能会导致Mock测试输出信息乱码
        converter.setDefaultCharset(StandardCharsets.UTF_8);

        ObjectMapper objectMapper = converter.getObjectMapper();
        objectMapper.setTimeZone(TimeZone.getDefault());
        objectMapper.setAnnotationIntrospector(new DateFormatterAnnotationIntrospector());
        return converter;
    }
}
