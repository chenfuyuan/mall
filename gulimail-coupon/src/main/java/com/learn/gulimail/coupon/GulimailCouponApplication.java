package com.learn.gulimail.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.learn.gulimail.coupon.dao")
public class GulimailCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailCouponApplication.class, args);
    }

}
