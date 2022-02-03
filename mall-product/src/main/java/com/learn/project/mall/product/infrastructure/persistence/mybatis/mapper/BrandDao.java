package com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper;

import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.BrandEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌Dao
 * 
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-12 14:35:18
 */
@Mapper
public interface BrandDao extends BaseMapper<BrandEntity> {
	
}
