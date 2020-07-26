package com.learn.gulimail.product.dao;

import com.learn.gulimail.product.entity.CommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 * 
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2020-07-26 13:41:52
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {
	
}
