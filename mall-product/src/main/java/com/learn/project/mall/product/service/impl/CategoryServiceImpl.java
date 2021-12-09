package com.learn.project.mall.product.service.impl;

import com.learn.project.common.utils.GlobalConstant;
import com.learn.project.mall.product.util.PmsConstant;
import org.springframework.stereotype.Service;

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

        //过滤出一级分类
        List<CategoryEntity> list = allCategory.stream().filter(category ->
                category.getCatLevel() == PmsConstant.CATEGORT_LEVEL_ONE
        ).map(category -> {
            //填充子分类
            category.setSubCategorys(getSubCategorys(category, allCategory));
            return category;
        }).sorted(CategoryEntity.getSortComparator()).collect(Collectors.toList());
        return list;
    }

    /**
     * 根据传过来的categorys获取其中以root为父级分类的所有分类集合
     *
     * @param root
     * @param all
     * @return
     */
    private List<CategoryEntity> getSubCategorys(CategoryEntity root, List<CategoryEntity> all) {
        if (root == null) {
            return null;
        }

        return all.stream().filter(category ->
                        root.getCatId().equals(category.getParentCid())
                ).map(category->{
                    category.setSubCategorys(getSubCategorys(category,all));
                    return category;
        }).sorted(CategoryEntity.getSortComparator()).collect(Collectors.toList());

    }

}