package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.CategoryBrandRelationCommand;
import com.learn.project.mall.product.application.dto.CategoryBrandRelationDto;
import com.learn.project.mall.product.domain.model.brand.Brand;
import com.learn.project.mall.product.domain.model.brand.BrandId;
import com.learn.project.mall.product.domain.model.category.Category;
import com.learn.project.mall.product.domain.model.category.CategoryId;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelation;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelationId;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 品牌分类关联-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class CategoryBrandRelationAssembler {

    /**
     * 转换成领域对象
     *
     * @param command 命令对象
     * @return
     */
    public static CategoryBrandRelation toCategoryBrandRelation(CategoryBrandRelationCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        CategoryBrandRelation result = new CategoryBrandRelation();

        //设置品牌
        Brand brand = new Brand();
        brand.setBrandId(new BrandId(command.getBrandId()));
        result.setBrand(brand);

        //设置分类
        Category category = new Category();
        category.setCategoryId(new CategoryId(command.getCatelogId()));
        result.setCategory(category);

        return result;
    }

    /**
     * 转换成数据传输对象
     *
     * @param categoryBrandRelation 领域对象
     * @return
     */
    public static CategoryBrandRelationDto fromCategoryBrandRelation(CategoryBrandRelation categoryBrandRelation) {
        if (EmptyUtil.isEmpty(categoryBrandRelation)) {
            return null;
        }
        CategoryBrandRelationDto result = new CategoryBrandRelationDto();
        result.setId(EntityId.getId(categoryBrandRelation.getId()));

        // 品牌
        if (EmptyUtil.isNotEmpty(categoryBrandRelation.getBrand())) {
            result.setBrandId(categoryBrandRelation.getBrand().getBrandId().getId());
            result.setBrandName(categoryBrandRelation.getBrand().getName());
        }

        //分类
        if (EmptyUtil.isNotEmpty(categoryBrandRelation.getCategory())) {
            result.setCatelogId(categoryBrandRelation.getCategory().getCategoryId().getId());
            result.setCatelogName(categoryBrandRelation.getCategory().getName());
        }

        if (categoryBrandRelation.getCommonInfo() != null) {
            result.setIsDelete(categoryBrandRelation.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(categoryBrandRelation.getCommonInfo().getCreateTime());
            result.setGmtModified(categoryBrandRelation.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(categoryBrandRelation.getCommonInfo().getVersion());
        }
        return result;
    }

    /**
     * 将集合转换成数据传输对象
     * @param categoryBrandRelation
     * @return
     */
    public static List<CategoryBrandRelationDto> fromCategoryBrandRelation(List<CategoryBrandRelation> categoryBrandRelation) {
        if (categoryBrandRelation == null) {
            return new ArrayList<>();
        }
        return ListUtil.listMapCollectToList(categoryBrandRelation, (item) -> fromCategoryBrandRelation(item));
    }
}