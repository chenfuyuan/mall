package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.learn.project.mall.product.application.CategoryCommandService;
import com.learn.project.mall.product.domain.model.category.Category;
import com.learn.project.mall.product.domain.model.category.CategoryRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryDO;
import com.uptool.core.constant.EmptyConstant;
import com.uptool.core.util.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public boolean removeCategoryByIds(Collection<Long> catIds) {
        if (EmptyUtil.isEmpty(catIds)) {
            return false;
        }
        //判断该分类是否含有子分类
        QueryWrapper<CategoryDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("parent_cid", catIds);
        queryWrapper.notIn("cat_id", catIds);
        List<Category> categoryList = categoryRepository.queryList(new HashMap<>(), queryWrapper);

        //过滤需要删除的元素
//        categoryEntities = categoryEntities.stream().filter(item ->
//                !catIds.contains(item.getCatId())
//        ).collect(Collectors.toList());
        if (EmptyUtil.isNotEmpty(categoryList)) {
            return false;
        }

        //删除分类
        return this.categoryRepository.removeByIds(catIds);
    }
}
