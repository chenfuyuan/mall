package com.learn.gulimail.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.learn.gulimail.ware.dao")
public class GulimailWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailWareApplication.class, args);
    }

}
