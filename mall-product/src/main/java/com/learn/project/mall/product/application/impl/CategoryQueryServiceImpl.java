package com.learn.project.mall.product.application.impl;



import com.learn.project.common.core.constant.GlobalConstant;
import com.learn.project.mall.product.application.assembler.CategoryAssembler;
import com.learn.project.mall.product.application.dto.CategoryDto;
import com.learn.project.mall.product.domain.model.category.Category;
import com.learn.project.mall.product.domain.model.category.CategoryId;
import com.learn.project.mall.product.domain.model.category.CategoryPath;
import com.learn.project.mall.product.domain.model.category.CategoryRepository;
import com.uptool.core.constant.EmptyConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryDo;
import com.learn.project.mall.product.application.CategoryQueryService;

/**
 * 商品三级分类Service实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-12 14:35:18
 */
@Service("categoryQueryService")
public class CategoryQueryServiceImpl implements CategoryQueryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> queryAllToTree() {
        //获取所有需要显示的分类
        QueryWrapper<CategoryDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("show_status", GlobalConstant.SHOW_STATUS_YES);

        List<Category> allCategoryList = categoryRepository.queryList(EmptyConstant.EMPTY_HASHMAP, queryWrapper);

        //调整为树形结构
        List<Category> categoryTree = Category.listToTree(allCategoryList);

        List<CategoryDto> result = new ArrayList<>(categoryTree.size());
        for (Category category : categoryTree) {
            result.add(CategoryAssembler.fromCategory(category));
        }

        return result;
    }


    @Override
    public Long[] findCategoryPathByCatId(Long catId) {
        CategoryPath categoryPath = categoryRepository.getCategoryPath(new CategoryId(catId));
        return categoryPath.getPath();
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.find(new CategoryId(id));
        return CategoryAssembler.fromCategory(category);
    }


}