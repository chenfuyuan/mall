package com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

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
public interface CategoryMapper extends BaseMapper<CategoryDO> {

    List<CategoryDO> queryList(@Param("params") Map<String, Object> params,@Param("es") QueryWrapper<CategoryDO> wrapper);

}
