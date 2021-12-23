package com.learn.project.mall.product.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.learn.project.common.utils.GlobalConstant;
import com.learn.project.mall.product.util.PmsConstant;
import com.uptool.core.util.EmptyUtil;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.common.utils.PageUtils;
import com.learn.project.common.utils.Query;

import com.learn.project.mall.product.dao.CategoryDao;
import com.learn.project.mall.product.entity.CategoryEntity;
import com.learn.project.mall.product.service.CategoryService;

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


}