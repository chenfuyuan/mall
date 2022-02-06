package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfo;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfoId;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfoRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.SkuInfoConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SkuInfoDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.SkuInfoMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * sku信息-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Repository
public class SkuInfoRepositoryImpl extends ServiceImpl<SkuInfoMapper, SkuInfoDo> implements SkuInfoRepository, IService<SkuInfoDo> {


    @Override
    public SkuInfo find(SkuInfoId skuId) {
        SkuInfoDo skuInfoDo = this.getById(skuId.getId());
        if (EmptyUtil.isNull(skuInfoDo)) {
            return null;
        }
        return SkuInfoConverter.toSkuInfo(skuInfoDo);
    }

    @Override
    public SkuInfoId store(SkuInfo skuInfo) {
        SkuInfoDo skuInfoDo = SkuInfoConverter.fromSkuInfo(skuInfo);
        this.saveOrUpdate(skuInfoDo);
        return new SkuInfoId(skuInfoDo.getSkuId());
    }

    @Override
    public boolean remove(Collection<SkuInfoId> skuInfoIdList) {
        List<Long> ids = new ArrayList<>();
        skuInfoIdList.forEach(skuInfoId -> ids.add(skuInfoId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(SkuInfoId skuInfoId) {
        return this.removeById(skuInfoId.getId());
    }

    @Override
    public Set<SkuInfoId> store(List<SkuInfo> skuInfoList) {
        List<SkuInfoDo> skuInfoDoList = ListUtil.listMapCollectToList(skuInfoList, skuInfo -> SkuInfoConverter.fromSkuInfo(skuInfo));
        this.saveOrUpdateBatch(skuInfoDoList);
        return skuInfoDoList.stream().map(skuInfoDo -> new SkuInfoId(skuInfoDo.getSkuId())).collect(Collectors.toSet());
    }

}
