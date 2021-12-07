package com.learn.project.mall.order.dao;

import com.learn.project.mall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-07 16:20:05
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
