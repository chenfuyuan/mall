package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.common.web.exception.BizException;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroup;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroupId;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroupRepository;
import com.learn.project.mall.product.domain.model.category.CategoryId;
import com.learn.project.mall.product.domain.model.category.CategoryPath;
import com.learn.project.mall.product.domain.model.category.CategoryRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.AttrGroupConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.AttrGroupDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.AttrGroupMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 属性分组-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Repository
public class AttrGroupRepositoryImpl extends ServiceImpl<AttrGroupMapper, AttrGroupDo> implements AttrGroupRepository, IService<AttrGroupDo> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public AttrGroup find(AttrGroupId attrGroupId) {
        AttrGroupDo attrGroupDo = this.getById(attrGroupId.getId());
        if (EmptyUtil.isNull(attrGroupDo)) {
            return null;
        }
        AttrGroup attrGroup = AttrGroupConverter.toAttrGroup(attrGroupDo);

        //查询所属分类路径，并填充
        CategoryPath categoryPath = categoryRepository.getCategoryPath(new CategoryId(attrGroup.getCatelogId()));
        if (EmptyUtil.isEmpty(categoryPath)) {
            throw new BizException("该属性分组查询不到分类路径!");
        }
        attrGroup.setCategoryPath(categoryPath);

        return attrGroup;
    }

    @Override
    public AttrGroupId store(AttrGroup attrGroup) {
        AttrGroupDo attrGroupDo = AttrGroupConverter.fromAttrGroup(attrGroup);
        this.saveOrUpdate(attrGroupDo);
        return new AttrGroupId(attrGroupDo.getAttrGroupId());
    }

    @Override
    public boolean remove(Collection<AttrGroupId> attrGroupIdList) {
        List<Long> ids = new ArrayList<>();
        attrGroupIdList.forEach(attrGroupId -> ids.add(attrGroupId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(AttrGroupId attrGroupId) {
        return this.removeById(attrGroupId.getId());
    }

    @Override
    public Set<AttrGroupId> store(List<AttrGroup> attrGroupList) {
        List<AttrGroupDo> attrGroupDoList = ListUtil.listMapCollectToList(attrGroupList, attrGroup -> AttrGroupConverter.fromAttrGroup(attrGroup));
        this.saveOrUpdateBatch(attrGroupDoList);
        return attrGroupDoList.stream().map(attrGroupDo -> new AttrGroupId(attrGroupDo.getAttrGroupId())).collect(Collectors.toSet());
    }

}
