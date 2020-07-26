package com.learn.gulimail.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.gulimail.coupon.entity.SeckillSkuNoticeEntity;

import java.util.Map;

/**
 * 秒杀商品通知订阅
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2020-07-27 04:16:46
 */
public interface SeckillSkuNoticeService extends IService<SeckillSkuNoticeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

