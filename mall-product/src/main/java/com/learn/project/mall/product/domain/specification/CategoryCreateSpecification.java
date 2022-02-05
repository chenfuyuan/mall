package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.mall.product.domain.model.category.Category;
import com.learn.project.mall.product.domain.model.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 分类 - 创建新对象检验
 *
 * @author chenfuyuan
 * @date 2022/2/4 18:18
 */
@Component
public class CategoryCreateSpecification extends AbstractSpecification<Category> {

    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryCreateSpecification(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    @Override
    public boolean isSatisfiedBy(Category category) {
        return true;
    }
}
