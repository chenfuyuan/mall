package com.learn.project.mall.member.feign;

import com.learn.project.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Coupon远程服务
 *
 * @author chenfuyuan
 * @date 2021/12/8 16:13
 */
@FeignClient("coupon")
public interface CouponFeignService {

    @RequestMapping("/coupon/coupon/member/list")
    public R membercoupons();


}
