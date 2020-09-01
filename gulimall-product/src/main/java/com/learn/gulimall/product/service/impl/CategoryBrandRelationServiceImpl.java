package com.learn.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.common.utils.PageUtils;
import com.learn.common.utils.Query;
import com.learn.gulimall.product.dao.BrandDao;
import com.learn.gulimall.product.dao.CategoryBrandRelationDao;
import com.learn.gulimall.product.dao.CategoryDao;
import com.learn.gulimall.product.entity.BrandEntity;
import com.learn.gulimall.product.entity.CategoryBrandRelationEntity;
import com.learn.gulimall.product.entity.CategoryEntity;
import com.learn.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private BrandDao brandDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );
        return new PageUtils(page);
    }

    /**
     * 保存详情。
     * @param categoryBrandRelation 品牌分类关联
     */
    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId =categoryBrandRelation.getBrandId();
        Long categoryId = categoryBrandRelation.getCatelogId();

        CategoryEntity categoryEntity = categoryDao.selectById(categoryId);

        BrandEntity brandEntity = brandDao.selectById(brandId);

        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());

        baseMapper.insert(categoryBrandRelation);
    }

    /**
     * 更新关联表,根据brandId更新name
     * @param brandId
     * @param name
     */
    @Override
    public void updateBrand(Long brandId, String name) {
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setBrandId(brandId);
        categoryBrandRelationEntity.setBrandName(name);
        this.baseMapper.update(categoryBrandRelationEntity, new UpdateWrapper<CategoryBrandRelationEntity>().eq(
                "brand_id", brandId));
    }

    /**
     * 更新关联表中的分类信息
     * @author: Vito.Chen
     * @date: 2020-8-13 22:59
     * @param catId
     * @param name
     * @return: void
     */
    @Override
    public void updateCategory(Long catId, String name) {
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setCatelogId(catId);
        categoryBrandRelationEntity.setCatelogName(name);
        this.baseMapper.update(categoryBrandRelationEntity, new UpdateWrapper<CategoryBrandRelationEntity>().eq(
                "catelog_id", catId));
    }
}