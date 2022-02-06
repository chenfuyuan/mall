package com.learn.project.mall.product.application;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.mall.product.application.dto.AttrDto;

import java.util.Map;

/**
 * 商品属性-查询服务接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface AttrQueryService {
    /**
     * 分页查询数据
     * @param params 查询参数
     * @return 分页结果
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过attrId获取指定数据
     * @param attrId attrId
     * @return 数据
     */
     AttrDto getById(Long attrId);
}
