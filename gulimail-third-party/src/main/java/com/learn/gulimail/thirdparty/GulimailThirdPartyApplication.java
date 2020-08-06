package com.learn.gulimail.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GulimailThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailThirdPartyApplication.class, args);
    }

}
