package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.brand.BrandId;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelation;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelationId;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelationRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.CategoryBrandRelationConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryBrandRelationDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.CategoryBrandRelationMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 品牌分类关联-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Repository
public class CategoryBrandRelationRepositoryImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelationDo> implements CategoryBrandRelationRepository, IService<CategoryBrandRelationDo> {


    @Override
    public CategoryBrandRelation find(CategoryBrandRelationId id) {
        CategoryBrandRelationDo categoryBrandRelationDo = this.getById(id.getId());
        if (EmptyUtil.isNull(categoryBrandRelationDo)) {
            return null;
        }
        return CategoryBrandRelationConverter.toCategoryBrandRelation(categoryBrandRelationDo);
    }

    @Override
    public List<CategoryBrandRelation> find(BrandId brandId) {
        QueryWrapper<CategoryBrandRelationDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", brandId.getId());
        List<CategoryBrandRelationDo> queryResult = this.list(queryWrapper);
        return ListUtil.listMapCollectToList(queryResult, item -> CategoryBrandRelationConverter.toCategoryBrandRelation(item));
    }

    @Override
    public CategoryBrandRelationId store(CategoryBrandRelation categoryBrandRelation) {
        CategoryBrandRelationDo categoryBrandRelationDo = CategoryBrandRelationConverter.fromCategoryBrandRelation(categoryBrandRelation);
        this.saveOrUpdate(categoryBrandRelationDo);
        return new CategoryBrandRelationId(categoryBrandRelationDo.getId());
    }

    @Override
    public boolean remove(Collection<CategoryBrandRelationId> categoryBrandRelationIdList) {
        List<Long> ids = new ArrayList<>();
        categoryBrandRelationIdList.forEach(categoryBrandRelationId -> ids.add(categoryBrandRelationId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(CategoryBrandRelationId categoryBrandRelationId) {
        return this.removeById(categoryBrandRelationId.getId());
    }

    @Override
    public Set<CategoryBrandRelationId> store(List<CategoryBrandRelation> categoryBrandRelationList) {
        List<CategoryBrandRelationDo> categoryBrandRelationDoList = ListUtil.listMapCollectToList(categoryBrandRelationList, categoryBrandRelation -> CategoryBrandRelationConverter.fromCategoryBrandRelation(categoryBrandRelation));
        this.saveOrUpdateBatch(categoryBrandRelationDoList);
        return categoryBrandRelationDoList.stream().map(categoryBrandRelationDo -> new CategoryBrandRelationId(categoryBrandRelationDo.getId())).collect(Collectors.toSet());
    }

}
