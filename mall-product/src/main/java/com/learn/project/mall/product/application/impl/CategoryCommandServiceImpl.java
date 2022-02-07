package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.project.mall.product.application.CategoryCommandService;
import com.learn.project.mall.product.application.assembler.CategoryAssembler;
import com.learn.project.mall.product.application.command.CategoryCommand;
import com.learn.project.mall.product.domain.model.category.Category;
import com.learn.project.mall.product.domain.model.category.CategoryId;
import com.learn.project.mall.product.domain.model.category.CategoryRepository;
import com.learn.project.mall.product.domain.specification.CategoryCreateSpecification;
import com.learn.project.mall.product.domain.specification.CategoryDeleteSpecification;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryDo;
import com.uptool.core.util.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author chenfuyuan
 * @date 2022/2/4 16:27
 */
@Service("categoryCommandService")
public class CategoryCommandServiceImpl implements CategoryCommandService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryCreateSpecification categoryCreateSpecification;

    @Override
    public boolean removeCategoryByIds(Collection<Long> catIds) {
        if (EmptyUtil.isEmpty(catIds)) {
            return false;
        }

        List<CategoryId> categoryIdList = new ArrayList<>();
        for (Long catId : catIds) {
            categoryIdList.add(new CategoryId(catId));
        }

        //判断该分类是否含有子分类
        CategoryDeleteSpecification categoryDeleteSpecification = new CategoryDeleteSpecification(categoryRepository);
        categoryDeleteSpecification.isSatisfiedBy(categoryIdList);

        //删除分类
        return this.categoryRepository.remove(categoryIdList);
    }

    @Override
    public void saveOrUpdate(CategoryCommand categoryCommand) {
        Category category = CategoryAssembler.toCategory(categoryCommand);
        categoryCreateSpecification.isSatisfiedBy(category);
        categoryRepository.store(category);
    }

    @Override
    public void saveOrUpdate(List<CategoryCommand> categoryCommandList) {
        List<Category> categoryList = CategoryAssembler.toCategoryList(categoryCommandList);
        for (Category category : categoryList) {
            categoryCreateSpecification.isSatisfiedBy(category);
        }
        categoryRepository.store(categoryList);
    }


}
