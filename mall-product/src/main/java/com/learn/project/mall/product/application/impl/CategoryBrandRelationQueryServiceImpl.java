package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.CategoryBrandRelationQueryService;
import com.learn.project.mall.product.application.assembler.CategoryBrandRelationAssembler;
import com.learn.project.mall.product.application.dto.CategoryBrandRelationDto;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelation;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelationId;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelationRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CategoryBrandRelationDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.CategoryBrandRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 品牌分类关联-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("categoryBrandRelationQueryService")
public class CategoryBrandRelationQueryServiceImpl implements CategoryBrandRelationQueryService {

    @Autowired
    private CategoryBrandRelationMapper categoryBrandRelationMapper;

    @Autowired
    private CategoryBrandRelationRepository categoryBrandRelationRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<CategoryBrandRelationDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<CategoryBrandRelationDo> page = categoryBrandRelationMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public CategoryBrandRelationDto getById(Long id) {
        CategoryBrandRelation categoryBrandRelation = categoryBrandRelationRepository.find(new CategoryBrandRelationId(id));
        return CategoryBrandRelationAssembler.fromCategoryBrandRelation(categoryBrandRelation);
    }


}
