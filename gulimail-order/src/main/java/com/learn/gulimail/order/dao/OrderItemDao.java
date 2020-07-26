package com.learn.gulimail.order.dao;

import com.learn.gulimail.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2020-07-27 04:40:56
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
