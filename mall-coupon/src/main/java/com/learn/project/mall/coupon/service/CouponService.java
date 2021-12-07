package com.learn.project.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.project.common.utils.PageUtils;
import com.learn.project.mall.coupon.entity.CouponEntity;

import java.util.Map;

/**
 * 优惠券信息
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-07 16:07:17
 */
public interface CouponService extends IService<CouponEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

