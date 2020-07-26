package com.learn.gulimail.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1. 整合Mybatis-plus
 *     1）、导入依赖
 *     mysql驱动
 *     2）、配置
 *         1、配置数据源
 *             1）、导入数据库驱动
 *             2）、application.yml配置数据源相关信息
 *         2、配置Mybatis-plus
 */
@MapperScan("com.learn.gulimail.product.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class GulimailProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailProductApplication.class, args);
    }

}
