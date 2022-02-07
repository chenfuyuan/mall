package com.learn.project.mall.product.domain.specification;

import com.learn.project.common.core.domain.AbstractSpecification;
import com.learn.project.common.web.exception.BizException;
import com.learn.project.mall.product.domain.model.category.Category;
import com.learn.project.mall.product.domain.model.category.CategoryId;
import com.learn.project.mall.product.domain.model.category.CategoryRepository;
import com.uptool.core.util.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * 三级分类-删除校验
 *
 * @author chenfuyuan
 * @date 2022/2/7 13:40
 */
public class CategoryDeleteSpecification extends AbstractSpecification<Collection<CategoryId>> {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryDeleteSpecification(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public boolean isSatisfiedBy(Collection<CategoryId> categoryIds) {
        List<Category> categoryList = categoryRepository.findSubCategory(categoryIds);
        if (EmptyUtil.isNotEmpty(categoryList)) {
            throw new BizException("请先删除子菜单!");
        }
        return true;
    }


}
