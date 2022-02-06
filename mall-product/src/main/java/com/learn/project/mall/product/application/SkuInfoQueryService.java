package com.learn.project.mall.product.application;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.mall.product.application.dto.SkuInfoDto;

import java.util.Map;

/**
 * sku信息-查询服务接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public interface SkuInfoQueryService {
    /**
     * 分页查询数据
     * @param params 查询参数
     * @return 分页结果
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过skuId获取指定数据
     * @param skuId skuId
     * @return 数据
     */
     SkuInfoDto getById(Long skuId);
}
