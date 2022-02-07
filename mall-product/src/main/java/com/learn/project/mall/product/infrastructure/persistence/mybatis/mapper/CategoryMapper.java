package com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类Dao
 * 
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-12 14:35:18
 */
@Mapper
public interface CategoryMapper extends BaseMapper<CategoryDo> {

    List<CategoryDo> queryList(@Param("params") Map<String, Object> params, @Param("ew") QueryWrapper<CategoryDo> wrapper);

}
