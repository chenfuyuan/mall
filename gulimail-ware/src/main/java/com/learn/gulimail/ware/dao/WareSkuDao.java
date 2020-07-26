package com.learn.gulimail.ware.dao;

import com.learn.gulimail.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2020-07-27 04:42:49
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
