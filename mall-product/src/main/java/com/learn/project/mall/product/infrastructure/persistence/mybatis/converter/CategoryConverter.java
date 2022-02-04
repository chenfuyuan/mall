package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;


import com.learn.project.mall.product.domain.model.category.Category;
import com.learn.project.mall.product.domain.model.category.CategoryId;
import com.learn.project.mall.product.domain.model.category.CategoryLevelEnum;
import com.learn.project.mall.product.domain.model.product.ProductCount;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryDO;

import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author chenfuyuan
 * @date 2022/2/4 0:02
 */
public class CategoryConverter {
    private CategoryConverter() {
    }


    public static Category toCategory(CategoryDO entity) {
        Category category = new Category();
        category.setCategoryId(new CategoryId(entity.getCatId()));
        category.setName(entity.getName());
        category.setCatLevel(CategoryLevelEnum.getCategoryLevelEnum(entity.getCatLevel()));
        category.setIcon(entity.getIcon());
        category.setProductCount(new ProductCount(entity.getProductCount()));
        category.setProductUnit(entity.getProductUnit());
        category.setParentId(new CategoryId(entity.getParentCid()));
        return category;
    }

    public static Category toCategory(CategoryDO categoryDo,CategoryDO parentCategory,List<CategoryDO> subCategoryList) {
        Category category = toCategory(categoryDo);

        category.setParent(toCategory(parentCategory));

        category.setSubCategorys(subCategoryList.stream().map(categoryDO -> {
            return toCategory(categoryDO);
        }).collect(Collectors.toList()));

        return category;
    }
}
