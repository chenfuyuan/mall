package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDesc;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDescId;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDescRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.SpuInfoDescConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SpuInfoDescDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.SpuInfoDescMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * spu信息介绍-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Repository
public class SpuInfoDescRepositoryImpl extends ServiceImpl<SpuInfoDescMapper, SpuInfoDescDo> implements SpuInfoDescRepository, IService<SpuInfoDescDo> {


    @Override
    public SpuInfoDesc find(SpuInfoDescId spuId) {
        SpuInfoDescDo spuInfoDescDo = this.getById(spuId.getId());
        if (EmptyUtil.isNull(spuInfoDescDo)) {
            return null;
        }
        return SpuInfoDescConverter.toSpuInfoDesc(spuInfoDescDo);
    }

    @Override
    public SpuInfoDescId store(SpuInfoDesc spuInfoDesc) {
        SpuInfoDescDo spuInfoDescDo = SpuInfoDescConverter.fromSpuInfoDesc(spuInfoDesc);
        this.saveOrUpdate(spuInfoDescDo);
        return new SpuInfoDescId(spuInfoDescDo.getSpuId());
    }

    @Override
    public boolean remove(Collection<SpuInfoDescId> spuInfoDescIdList) {
        List<Long> ids = new ArrayList<>();
        spuInfoDescIdList.forEach(spuInfoDescId -> ids.add(spuInfoDescId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(SpuInfoDescId spuInfoDescId) {
        return this.removeById(spuInfoDescId.getId());
    }

    @Override
    public Set<SpuInfoDescId> store(List<SpuInfoDesc> spuInfoDescList) {
        List<SpuInfoDescDo> spuInfoDescDoList = ListUtil.listMapCollectToList(spuInfoDescList, spuInfoDesc -> SpuInfoDescConverter.fromSpuInfoDesc(spuInfoDesc));
        this.saveOrUpdateBatch(spuInfoDescDoList);
        return spuInfoDescDoList.stream().map(spuInfoDescDo -> new SpuInfoDescId(spuInfoDescDo.getSpuId())).collect(Collectors.toSet());
    }

}
