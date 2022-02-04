package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.category.Category;
import com.learn.project.mall.product.domain.model.category.CategoryId;
import com.learn.project.mall.product.domain.model.category.CategoryPath;
import com.learn.project.mall.product.domain.model.category.CategoryRepository;
import com.learn.project.mall.product.infrastructure.constant.PmsConstant;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.CategoryConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryDO;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.CategoryMapper;
import com.uptool.core.util.EmptyUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分类-Repository实现类
 * @author chenfuyuan
 * @date 2022/2/3 23:48
 */
@Service("CategoryRepository")
public class CategoryRepositoryImpl extends ServiceImpl<CategoryMapper,CategoryDO> implements CategoryRepository {

    @Override
    public List<Category> queryList(Map<String, Object> params, QueryWrapper<CategoryDO> wrapper) {
        List<CategoryDO> queryList = getBaseMapper().queryList(params,wrapper);

        return queryList.stream()
                .map(categoryDO -> CategoryConverter.toCategory(categoryDO))
                .collect(Collectors.toList());
    }

    @Override
    public Category find(CategoryId categoryId) {
        CategoryDO categoryDO = this.getById(categoryId.getId());
        if (categoryDO == null) {
            return null;
        }

        return CategoryConverter.toCategory(categoryDO);
    }

    @Override
    public CategoryPath getCategoryPath(CategoryId categoryId) {
        CategoryDO categoryDO = this.getById(categoryId.getId());
        //查询分类路径
        Long[] pathArray = getCategoryPath(categoryDO);
        return new CategoryPath(pathArray);
    }

    private Long[] getCategoryPath(CategoryDO category) {
        if (EmptyUtil.isEmpty(category.getParentCid())) {
            return new Long[]{category.getCatId()};
        }

        List<Long> path = new ArrayList<>(PmsConstant.CATEGORY_MAX_LEVEL);
        getCategoryPathByCatIdRecursion(category, path);

        //翻转
        Collections.reverse(path);
        return path.toArray(new Long[path.size()]);
    }

    private void getCategoryPathByCatIdRecursion(CategoryDO category, List<Long> path) {
        if (EmptyUtil.isEmpty(category)) {
            return;
        }
        path.add(category.getCatId());
        CategoryDO parentCategory = this.getById(category.getParentCid());
        getCategoryPathByCatIdRecursion(parentCategory, path);
    }


}
