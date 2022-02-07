package com.learn.project.mall.product.domain.model.category;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryDo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chenfuyuan
 * @date 2022/2/3 23:38
 */
public interface CategoryRepository  {

    /**
     * 查询列表
     *
     * @param params
     * @return
     */
    List<Category> queryList(Map<String, Object> params,  QueryWrapper<CategoryDo> wrapper);

    /**
     * 查找分类-通过Id
     * @param categoryId 分类Id
     * @return
     */
    Category find(CategoryId categoryId);

    /**
     * 查找分类路径-通过Id
     * @param categoryId
     * @return
     */
    CategoryPath getCategoryPath(CategoryId categoryId);

    CategoryId store(Category category);

    void store(List<Category> categoryList);

    boolean remove(Collection<CategoryId> catIds);

    List<Category> findSubCategory(Collection<CategoryId> categoryIds);
}
