package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.attr.Attr;
import com.learn.project.mall.product.domain.model.attr.AttrId;
import com.learn.project.mall.product.domain.model.attr.AttrRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.AttrConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.AttrDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.AttrMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 商品属性-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Repository
public class AttrRepositoryImpl extends ServiceImpl<AttrMapper, AttrDo> implements AttrRepository, IService<AttrDo> {


    @Override
    public Attr find(AttrId attrId) {
        AttrDo attrDo = this.getById(attrId.getId());
        if (EmptyUtil.isNull(attrDo)) {
            return null;
        }
        return AttrConverter.toAttr(attrDo);
    }

    @Override
    public AttrId store(Attr attr) {
        AttrDo attrDo = AttrConverter.fromAttr(attr);
        this.saveOrUpdate(attrDo);
        return new AttrId(attrDo.getAttrId());
    }

    @Override
    public boolean remove(Collection<AttrId> attrIdList) {
        List<Long> ids = new ArrayList<>();
        attrIdList.forEach(attrId -> ids.add(attrId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(AttrId attrId) {
        return this.removeById(attrId.getId());
    }

    @Override
    public Set<AttrId> store(List<Attr> attrList) {
        List<AttrDo> attrDoList = ListUtil.listMapCollectToList(attrList, attr -> AttrConverter.fromAttr(attr));
        this.saveOrUpdateBatch(attrDoList);
        return attrDoList.stream().map(attrDo -> new AttrId(attrDo.getAttrId())).collect(Collectors.toSet());
    }

}
