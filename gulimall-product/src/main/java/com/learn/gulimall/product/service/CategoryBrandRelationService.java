package com.learn.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.gulimall.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2020-08-09 14:43:22
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);


    /**
     * 保存详情
     *
     * @param categoryBrandRelation 品牌分类关联
     */
    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    /**
     * 更新与brandId关联的品牌名
     *
     * @param brandId
     * @param name
     */
    void updateBrand(Long brandId, String name);

    /**
     * 更新关联表中的分类信息
     * @param catId 分类id
     * @param name 名字
     * @author: Vito.Chen
     * @date: 2020-8-13 22:58
     * @return: void
     */
    void updateCategory(Long catId, String name);

}

