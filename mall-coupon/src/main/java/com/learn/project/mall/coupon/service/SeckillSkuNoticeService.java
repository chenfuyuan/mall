package com.learn.project.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.project.common.utils.PageUtils;
import com.learn.project.mall.coupon.entity.SeckillSkuNoticeEntity;

import java.util.Map;

/**
 * 秒杀商品通知订阅
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-07 16:07:17
 */
public interface SeckillSkuNoticeService extends IService<SeckillSkuNoticeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

