package com.learn.gulimail.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.gulimail.order.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * 退货原因
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2020-07-27 04:40:56
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

