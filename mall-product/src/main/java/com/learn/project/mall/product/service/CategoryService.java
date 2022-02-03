package com.learn.project.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.project.common.utils.PageUtils;
import com.learn.project.mall.product.entity.CategoryEntity;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品三级分类Service接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-12 14:35:18
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);


    /**
     * 获取当前分类及其所有子分类。并将其组合成树状结构进行返回
     * @return
     */
    List<CategoryEntity> listByTree();

    /**
     * 根据catId删除指定的分类
     * @param catIds 需要删除的分类id
     * @return 是否删除成功
     */
    boolean removeCategoryByIds(Collection<Long> catIds);

    /**
     * 根据id获取分类
     * @param catId 分类id
     * @return
     */
    Long[] findCategoryPathByCatId(Long catId);

}

