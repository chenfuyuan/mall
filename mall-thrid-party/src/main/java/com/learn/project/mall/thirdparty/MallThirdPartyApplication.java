package com.learn.project.mall.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author chenfuyuan
 * @date 2022/1/6 14:30
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages =  "com.learn.project.mall.thirdparty.feign")
public class MallThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallThirdPartyApplication.class, args);
    }

}
