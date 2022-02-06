package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.CategoryBrandRelationCommandService;
import com.learn.project.mall.product.application.assembler.CategoryBrandRelationAssembler;
import com.learn.project.mall.product.application.command.CategoryBrandRelationCommand;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelation;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelationId;
import com.learn.project.mall.product.domain.model.categoryBrandRelation.CategoryBrandRelationRepository;
import com.learn.project.mall.product.domain.specification.CategoryBrandRelationCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 品牌分类关联-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("categoryBrandRelationCommandService")
public class CategoryBrandRelationCommandServiceImpl implements CategoryBrandRelationCommandService {

    @Autowired
    private CategoryBrandRelationRepository categoryBrandRelationRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(CategoryBrandRelationCommand categoryBrandRelationCommand) {
        CategoryBrandRelation categoryBrandRelation = CategoryBrandRelationAssembler.toCategoryBrandRelation(categoryBrandRelationCommand);
        CategoryBrandRelationCreateSpecification categoryBrandRelationCreateSpecification = new CategoryBrandRelationCreateSpecification();
        categoryBrandRelationCreateSpecification.isSatisfiedBy(categoryBrandRelation);
        return categoryBrandRelationRepository.store(categoryBrandRelation).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> ids) {
        if (EmptyUtil.isEmpty(ids)) {
            return false;
        }

        List<CategoryBrandRelationId> categoryBrandRelationIdList = new ArrayList<>();
        for (Long id : ids) {
            categoryBrandRelationIdList.add(new CategoryBrandRelationId(id));
        }
        return categoryBrandRelationRepository.remove(categoryBrandRelationIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return categoryBrandRelationRepository.remove(new CategoryBrandRelationId(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<CategoryBrandRelationCommand> categoryBrandRelationCommandList) {
        List<CategoryBrandRelation> categoryBrandRelationList = ListUtil.listMapCollectToList(categoryBrandRelationCommandList, command ->
                CategoryBrandRelationAssembler.toCategoryBrandRelation(command)
        );

        //校验
        CategoryBrandRelationCreateSpecification categoryBrandRelationCreateSpecification = new CategoryBrandRelationCreateSpecification();
        categoryBrandRelationList.forEach(categoryBrandRelation->{
            categoryBrandRelationCreateSpecification.isSatisfiedBy(categoryBrandRelation);
        });

        return categoryBrandRelationRepository.store(categoryBrandRelationList).stream().map(id->id.getId()).toArray(Long[]::new);
    }
}
