package com.learn.gulimail.member.feign;

import com.learn.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName CouponFeignService
 * @Description TODO
 * @Author chenfuyuan
 * @Date 2020-7-27 7:38
 * @Version 1.0
 */
@FeignClient("gulimail-coupon")
public interface CouponFeignService {

    @RequestMapping("/coupon/coupon/member/list")
    public R memberCoupons();

}
