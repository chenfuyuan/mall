package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfo;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfoId;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfoRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.SpuInfoConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SpuInfoDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.SpuInfoMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * spu信息-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Repository
public class SpuInfoRepositoryImpl extends ServiceImpl<SpuInfoMapper, SpuInfoDo> implements SpuInfoRepository, IService<SpuInfoDo> {


    @Override
    public SpuInfo find(SpuInfoId id) {
        SpuInfoDo spuInfoDo = this.getById(id.getId());
        if (EmptyUtil.isNull(spuInfoDo)) {
            return null;
        }
        return SpuInfoConverter.toSpuInfo(spuInfoDo);
    }

    @Override
    public SpuInfoId store(SpuInfo spuInfo) {
        SpuInfoDo spuInfoDo = SpuInfoConverter.fromSpuInfo(spuInfo);
        this.saveOrUpdate(spuInfoDo);
        return new SpuInfoId(spuInfoDo.getId());
    }

    @Override
    public boolean remove(Collection<SpuInfoId> spuInfoIdList) {
        List<Long> ids = new ArrayList<>();
        spuInfoIdList.forEach(spuInfoId -> ids.add(spuInfoId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(SpuInfoId spuInfoId) {
        return this.removeById(spuInfoId.getId());
    }

    @Override
    public Set<SpuInfoId> store(List<SpuInfo> spuInfoList) {
        List<SpuInfoDo> spuInfoDoList = ListUtil.listMapCollectToList(spuInfoList, spuInfo -> SpuInfoConverter.fromSpuInfo(spuInfo));
        this.saveOrUpdateBatch(spuInfoDoList);
        return spuInfoDoList.stream().map(spuInfoDo -> new SpuInfoId(spuInfoDo.getId())).collect(Collectors.toSet());
    }

}
