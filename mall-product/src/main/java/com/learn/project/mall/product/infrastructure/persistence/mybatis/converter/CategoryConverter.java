package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;


import com.learn.project.common.mybatis.util.BaseDo;
import com.learn.project.common.web.constant.ShowStatusEnum;
import com.learn.project.mall.product.domain.model.category.Category;
import com.learn.project.mall.product.domain.model.category.CategoryId;
import com.learn.project.mall.product.domain.model.category.CategoryLevelEnum;
import com.learn.project.mall.product.domain.model.product.ProductCount;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryDo;

import java.util.ArrayList;
import java.util.Date;
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


    public static Category toCategory(CategoryDo entity) {
        Category category = new Category();
        category.setCategoryId(new CategoryId(entity.getCatId()));
        category.setName(entity.getName());
        category.setCatLevel(CategoryLevelEnum.getCategoryLevelEnum(entity.getCatLevel()));
        category.setIcon(entity.getIcon());
        category.setProductCount(new ProductCount(entity.getProductCount()));
        category.setProductUnit(entity.getProductUnit());
        category.setParentId(new CategoryId(entity.getParentCid()));
        category.setShowStatus(ShowStatusEnum.getShowStatusEnum(entity.getShowStatus()));
        category.setSort(entity.getSort());
        category.setTimeInfo(BaseDo.getCommonInfo(entity));
        return category;
    }

    public static Category toCategory(CategoryDo categoryDo, CategoryDo parentCategory, List<CategoryDo> subCategoryList) {
        Category category = toCategory(categoryDo);

        category.setParent(toCategory(parentCategory));

        category.setSubCategorys(subCategoryList.stream().map(categoryDO -> {
            return toCategory(categoryDO);
        }).collect(Collectors.toList()));

        return category;
    }

    public static CategoryDo fromCategory(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDo result = new CategoryDo();

        if (category.getCategoryId() == null || category.getCategoryId() == null) {
            result.setGmtCreate(new Date());
            result.setUpdateVersion(0);
        }else{
            result.setCatId(category.getCategoryId() == null ? null : category.getCategoryId().getId());
        }

        result.setName(category.getName());
        result.setParentCid(category.getParentId() == null ? null : category.getParentId().getId());
        result.setCatLevel(category.getCatLevel() == null ? null : category.getCatLevel().getValue());
        result.setShowStatus(category.getShowStatus() == null ? null : category.getShowStatus().getValue());
        result.setSort(category.getSort());
        result.setIcon(category.getIcon());
        result.setProductUnit(category.getProductUnit());
        result.setProductCount(category.getProductCount() == null ? null : category.getProductCount().getCount());
        result.setIsDelete(null);
        result.setGmtModified(new Date());

        return result;
    }

    public static List<CategoryDo> fromCategory(List<Category> categoryList) {
        if (categoryList == null) {
            return null;
        }

        List<CategoryDo> result = new ArrayList<>(categoryList.size());

        for (Category category : categoryList) {
            result.add(fromCategory(category));
        }

        return result;
    }
}
