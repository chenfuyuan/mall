package com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SpuInfoDo;
import org.apache.ibatis.annotations.Mapper;

/**
 * spu信息-数据库访问接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Mapper
public interface SpuInfoMapper extends BaseMapper<SpuInfoDo> {


}
