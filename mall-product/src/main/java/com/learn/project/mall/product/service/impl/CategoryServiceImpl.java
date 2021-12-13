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

    @Override
    public boolean removeCategoryByIds(Collection<Long> catIds) {
        if (EmptyUtil.isEmpty(catIds)) {
            return false;
        }
        //判断该分类是否含有子分类
        QueryWrapper<CategoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("show_status", "1");
        queryWrapper.in("parent_cid", catIds);
        List<CategoryEntity> categoryEntities = baseMapper.selectList(queryWrapper);
        if (EmptyUtil.isNotEmpty(categoryEntities)) {
            return false;
        }

        //删除分类
        return SqlHelper.retBool(this.baseMapper.deleteBatchIds(catIds));
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