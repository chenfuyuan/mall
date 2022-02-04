package com.learn.project.mall.product.application;

import com.learn.project.mall.product.application.dto.CategoryDto;

import java.util.Collection;
import java.util.List;

/**
 * 商品三级分类Service接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-12 14:35:18
 */
public interface CategoryQueryService {

    /**
     * 获取当前分类及其所有子分类。并将其组合成树状结构进行返回
     * @return
     */
    List<CategoryDto> queryAllToTree();

    /**
     * 根据id获取分类
     * @param catId 分类id
     * @return
     */
    Long[] findCategoryPathByCatId(Long catId);

    CategoryDto getById(Long id);

}

