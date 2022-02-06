package com.learn.project.mall.product.application;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.mall.product.application.dto.ProductAttrValueDto;

import java.util.Map;

/**
 * spu属性值-查询服务接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface ProductAttrValueQueryService {
    /**
     * 分页查询数据
     * @param params 查询参数
     * @return 分页结果
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过id获取指定数据
     * @param id id
     * @return 数据
     */
     ProductAttrValueDto getById(Long id);
}
