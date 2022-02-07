package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.category.Category;
import com.learn.project.mall.product.domain.model.category.CategoryId;
import com.learn.project.mall.product.domain.model.category.CategoryPath;
import com.learn.project.mall.product.domain.model.category.CategoryRepository;
import com.learn.project.mall.product.infrastructure.constant.PmsConstant;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.CategoryConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.CategoryMapper;
import com.uptool.core.util.EmptyUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分类-Repository实现类
 * @author chenfuyuan
 * @date 2022/2/3 23:48
 */
@Service("CategoryRepository")
public class CategoryRepositoryImpl extends ServiceImpl<CategoryMapper, CategoryDo> implements CategoryRepository, IService<CategoryDo> {

    @Override
    public List<Category> queryList(Map<String, Object> params, QueryWrapper<CategoryDo> wrapper) {
        List<CategoryDo> queryList = getBaseMapper().queryList(params,wrapper);
        return queryList.stream()
                .map(categoryDO -> CategoryConverter.toCategory(categoryDO))
                .collect(Collectors.toList());
    }

    @Override
    public Category find(CategoryId categoryId) {
        CategoryDo categoryDO = this.getById(categoryId.getId());
        if (categoryDO == null) {
            return null;
        }

        return CategoryConverter.toCategory(categoryDO);
    }

    @Override
    public CategoryPath getCategoryPath(CategoryId categoryId) {
        //查询需要寻找路径的分类Id
        CategoryDo categoryDO = this.getById(categoryId.getId());
        if (categoryDO == null) {
            return new CategoryPath(new Long[0]);
        }
        //查询分类路径
        Long[] pathArray = getCategoryPath(categoryDO);
        return new CategoryPath(pathArray);
    }

    @Override
    public CategoryId store(Category category) {
        CategoryDo categoryEntity = CategoryConverter.fromCategory(category);
        this.saveOrUpdate(categoryEntity);
        return new CategoryId(categoryEntity.getCatId());
    }

    @Override
    public void store(List<Category> categoryList) {
        List<CategoryDo> categoryDoList = CategoryConverter.fromCategory(categoryList);
        this.saveOrUpdateBatch(categoryDoList);
    }

    @Override
    public boolean remove(Collection<CategoryId> categoryIds) {
        List<Long> ids = new ArrayList<>();
        categoryIds.forEach(categoryId -> ids.add(categoryId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public List<Category> findSubCategory(Collection<CategoryId> categoryIds) {
        if (EmptyUtil.isEmpty(categoryIds)) {
            return new ArrayList<>();
        }
        Long[] catIds = categoryIds.stream().map(id-> id.getId()).toArray(Long[]::new);
        QueryWrapper<CategoryDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("parent_cid", catIds);
        queryWrapper.notIn("cat_id", catIds);
        return this.queryList(new HashMap<>(), queryWrapper);
    }


    private Long[] getCategoryPath(CategoryDo category) {
        if (EmptyUtil.isEmpty(category.getParentCid())) {
            return new Long[]{category.getCatId()};
        }

        List<Long> path = new ArrayList<>(PmsConstant.CATEGORY_MAX_LEVEL);
        getCategoryPathByCatIdRecursion(category, path);

        //翻转
        Collections.reverse(path);
        return path.toArray(new Long[path.size()]);
    }

    private void getCategoryPathByCatIdRecursion(CategoryDo category, List<Long> path) {
        if (EmptyUtil.isEmpty(category)) {
            return;
        }
        path.add(category.getCatId());
        CategoryDo parentCategory = this.getById(category.getParentCid());
        getCategoryPathByCatIdRecursion(parentCategory, path);
    }


}
