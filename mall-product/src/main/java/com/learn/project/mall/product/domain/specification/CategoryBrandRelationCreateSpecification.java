package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.brand.Brand;
import com.learn.project.mall.product.domain.model.brand.BrandRepository;
import com.learn.project.mall.product.domain.model.category.Category;
import com.learn.project.mall.product.domain.model.category.CategoryRepository;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelation;
import com.uptool.core.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 品牌分类关联-创建时校验类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Component
public class CategoryBrandRelationCreateSpecification extends AbstractSpecification<CategoryBrandRelation> {

    private CategoryRepository categoryRepository;

    private BrandRepository brandRepository;

    @Autowired
    public CategoryBrandRelationCreateSpecification(CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public boolean isSatisfiedBy(CategoryBrandRelation categoryBrandRelation) {
        StringBuffer stringBuffer = new StringBuffer();
        Category category = categoryRepository.find(categoryBrandRelation.getCategory().getCategoryId());
        Brand brand = brandRepository.find(categoryBrandRelation.getBrand().getBrandId());
        ValidationUtil.newInstance().checkEmpty(category, "关联分类不存在!").checkEmpty(brand, "关联品牌不存在!");

        //将名称信息进行填充
        categoryBrandRelation.setCategory(category);
        categoryBrandRelation.setBrand(brand);

        return true;
    }
}
