package com.learn.project.mall.product.application;

import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.mall.product.application.dto.CategoryBrandRelationDto;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联-查询服务接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface CategoryBrandRelationQueryService {
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
     CategoryBrandRelationDto getById(Long id);

    /**
     * 通过品牌Id获取指定数据
     * @param brandId 品牌Id
     * @return
     */
    List<CategoryBrandRelationDto> queryByBrandId(Long brandId);
}
