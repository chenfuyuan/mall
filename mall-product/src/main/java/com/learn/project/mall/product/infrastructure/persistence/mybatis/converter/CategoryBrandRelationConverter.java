package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.brand.Brand;
import com.learn.project.mall.product.domain.model.brand.BrandId;
import com.learn.project.mall.product.domain.model.category.Category;
import com.learn.project.mall.product.domain.model.category.CategoryId;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelation;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelationId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryBrandRelationDo;
import com.uptool.core.util.EmptyUtil;

/**
 * 品牌分类关联-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class CategoryBrandRelationConverter {

    /**
     * 将领域对象转化为数据对象
     * @param categoryBrandRelation 领域对象
     * @return
     */
    public static CategoryBrandRelationDo fromCategoryBrandRelation(CategoryBrandRelation categoryBrandRelation) {
        if (EmptyUtil.isNull(categoryBrandRelation)) {
            return null;
        }
        CategoryBrandRelationDo result = new CategoryBrandRelationDo();
        ConverterUtil.fromDomainModelCommonInfo(result,categoryBrandRelation,categoryBrandRelation);

        result.setBrandId(categoryBrandRelation.getBrand().getBrandId().getId());
        result.setBrandName(categoryBrandRelation.getBrand().getName());

        result.setCatelogId(categoryBrandRelation.getCategory().getCategoryId().getId());
        result.setCatelogName(categoryBrandRelation.getCategory().getName());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param categoryBrandRelationDo 数据对象
     * @return
     */
    public static CategoryBrandRelation toCategoryBrandRelation(CategoryBrandRelationDo categoryBrandRelationDo) {
        if (EmptyUtil.isNull(categoryBrandRelationDo)) {
            return null;
        }

        CategoryBrandRelation result = new CategoryBrandRelation();
        ConverterUtil.toDomainModelCommonInfo(result, categoryBrandRelationDo);

        result.setId(new CategoryBrandRelationId(categoryBrandRelationDo.getId()));

        Brand brand = new Brand();
        brand.setBrandId(new BrandId(categoryBrandRelationDo.getId()));
        brand.setName(categoryBrandRelationDo.getBrandName());
        result.setBrand(brand);

        Category category = new Category();
        category.setCategoryId(new CategoryId(categoryBrandRelationDo.getId()));
        category.setName(categoryBrandRelationDo.getCatelogName());
        result.setCategory(category);

        return result;
    }
}
