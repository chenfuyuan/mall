package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelation;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelationId;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelationRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.AttrAttrgroupRelationConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.AttrAttrgroupRelationDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.AttrAttrgroupRelationMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 属性&属性分组关联-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Repository
public class AttrAttrgroupRelationRepositoryImpl extends ServiceImpl<AttrAttrgroupRelationMapper, AttrAttrgroupRelationDo> implements AttrAttrgroupRelationRepository, IService<AttrAttrgroupRelationDo> {


    @Override
    public AttrAttrgroupRelation find(AttrAttrgroupRelationId id) {
        AttrAttrgroupRelationDo attrAttrgroupRelationDo = this.getById(id.getId());
        if (EmptyUtil.isNull(attrAttrgroupRelationDo)) {
            return null;
        }
        return AttrAttrgroupRelationConverter.toAttrAttrgroupRelation(attrAttrgroupRelationDo);
    }

    @Override
    public AttrAttrgroupRelationId store(AttrAttrgroupRelation attrAttrgroupRelation) {
        AttrAttrgroupRelationDo attrAttrgroupRelationDo = AttrAttrgroupRelationConverter.fromAttrAttrgroupRelation(attrAttrgroupRelation);
        this.saveOrUpdate(attrAttrgroupRelationDo);
        return new AttrAttrgroupRelationId(attrAttrgroupRelationDo.getId());
    }

    @Override
    public boolean remove(Collection<AttrAttrgroupRelationId> attrAttrgroupRelationIdList) {
        List<Long> ids = new ArrayList<>();
        attrAttrgroupRelationIdList.forEach(attrAttrgroupRelationId -> ids.add(attrAttrgroupRelationId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(AttrAttrgroupRelationId attrAttrgroupRelationId) {
        return this.removeById(attrAttrgroupRelationId.getId());
    }

    @Override
    public Set<AttrAttrgroupRelationId> store(List<AttrAttrgroupRelation> attrAttrgroupRelationList) {
        List<AttrAttrgroupRelationDo> attrAttrgroupRelationDoList = ListUtil.listMapCollectToList(attrAttrgroupRelationList, attrAttrgroupRelation -> AttrAttrgroupRelationConverter.fromAttrAttrgroupRelation(attrAttrgroupRelation));
        this.saveOrUpdateBatch(attrAttrgroupRelationDoList);
        return attrAttrgroupRelationDoList.stream().map(attrAttrgroupRelationDo -> new AttrAttrgroupRelationId(attrAttrgroupRelationDo.getId())).collect(Collectors.toSet());
    }

}
