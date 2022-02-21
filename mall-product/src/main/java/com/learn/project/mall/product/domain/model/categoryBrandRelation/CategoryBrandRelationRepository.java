package com.learn.project.mall.product.domain.model.categoryBrandRelation;

import com.learn.project.mall.product.domain.model.brand.BrandId;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 品牌分类关联-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface CategoryBrandRelationRepository {

    /**
     * 通过id查找
     * @param id id
     * @return
     */
    CategoryBrandRelation find(CategoryBrandRelationId id);

    /**
     * 通过品牌Id查找 品牌对应关系
     * @param brandId 品牌Id
     * @return
     */
    List<CategoryBrandRelation> find(BrandId brandId);

    /**
     * 存储领域对象
     * @param categoryBrandRelation
     */
    CategoryBrandRelationId store(CategoryBrandRelation categoryBrandRelation);

    /**
     * 根据传递过来的id进行删除
     * @param categoryBrandRelationIdList id列表
     * @return
     */
    boolean remove(Collection<CategoryBrandRelationId> categoryBrandRelationIdList);

    /**
     * 根据id进行删除
     * @param categoryBrandRelationId id
     * @return
     */
    boolean remove(CategoryBrandRelationId categoryBrandRelationId);

    Set<CategoryBrandRelationId> store(List<CategoryBrandRelation> categoryBrandRelationList);
}
