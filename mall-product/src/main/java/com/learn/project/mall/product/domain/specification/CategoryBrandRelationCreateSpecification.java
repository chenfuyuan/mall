package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelation;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 品牌分类关联-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Component
public class CategoryBrandRelationCreateSpecification extends AbstractSpecification<CategoryBrandRelation> {

    /**
     * 仓储服务
     */
    private final CategoryBrandRelationRepository categoryBrandRelationRepository;

    @Autowired
    public CategoryBrandRelationCreateSpecification(CategoryBrandRelationRepository categoryBrandRelationRepository) {
        this.categoryBrandRelationRepository = categoryBrandRelationRepository;
    }

    @Override
    public boolean isSatisfiedBy(CategoryBrandRelation categoryBrandRelation) {
        return true;
    }
}
