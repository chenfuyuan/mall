package com.learn.project.mall.product.dao;

import com.learn.project.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-06 17:28:37
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
