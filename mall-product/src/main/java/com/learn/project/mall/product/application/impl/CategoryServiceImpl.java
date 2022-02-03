package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;

import com.learn.project.common.core.constant.GlobalConstant;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.infrastructure.constant.PmsConstant;
import com.uptool.core.util.EmptyUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.common.mybatis.util.PageUtils;

import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.CategoryDao;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryEntity;
import com.learn.project.mall.product.application.CategoryService;

/**
 * 商品三级分类Service实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-12 14:35:18
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listByTree() {
        //获取所有需要显示的分类
        QueryWrapper<CategoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("show_status", GlobalConstant.SHOW_STATUS_YES);
        List<CategoryEntity> allCategory = baseMapper.selectList(queryWrapper);

        //调整为树形结构
        List<CategoryEntity> categoryTree = CategoryEntity.listToTree(allCategory);
        return categoryTree;
    }

    @Override
    public boolean removeCategoryByIds(Collection<Long> catIds) {
        if (EmptyUtil.isEmpty(catIds)) {
            return false;
        }
        //判断该分类是否含有子分类
        QueryWrapper<CategoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("parent_cid", catIds);
        queryWrapper.notIn("cat_id", catIds);
        List<CategoryEntity> categoryEntities = baseMapper.selectList(queryWrapper);

        //过滤需要删除的元素
//        categoryEntities = categoryEntities.stream().filter(item ->
//                !catIds.contains(item.getCatId())
//        ).collect(Collectors.toList());
        if (EmptyUtil.isNotEmpty(categoryEntities)) {
            return false;
        }

        //删除分类
        return SqlHelper.retBool(this.baseMapper.deleteBatchIds(catIds));
    }

    @Override
    public Long[] findCategoryPathByCatId(Long catId) {
        CategoryEntity category = this.getById(catId);

        //查询分类路径
        Long[] categoryPath = getCategoryPath(category);

        return categoryPath;
    }

    private Long[] getCategoryPath(CategoryEntity category) {
        if (EmptyUtil.isEmpty(category.getParentCid())) {
            return new Long[]{category.getCatId()};
        }

        List<Long> path = new ArrayList<>(PmsConstant.CATEGORY_MAX_LEVEL);
        getCategoryPathByCatIdRecursion(category, path);

        //翻转
        Collections.reverse(path);
        return path.toArray(new Long[path.size()]);
    }


    private void getCategoryPathByCatIdRecursion(CategoryEntity category,List<Long> path) {
        if (EmptyUtil.isEmpty(category)) {
            return;
        }
        path.add(category.getCatId());
        CategoryEntity parentCategory = this.getById(category.getParentCid());
        getCategoryPathByCatIdRecursion(parentCategory, path);
    }


}