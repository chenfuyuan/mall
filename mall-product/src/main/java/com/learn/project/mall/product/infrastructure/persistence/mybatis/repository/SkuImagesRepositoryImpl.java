package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.skuImages.SkuImages;
import com.learn.project.mall.product.domain.model.skuImages.SkuImagesId;
import com.learn.project.mall.product.domain.model.skuImages.SkuImagesRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.SkuImagesConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SkuImagesDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.SkuImagesMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * sku图片-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Repository
public class SkuImagesRepositoryImpl extends ServiceImpl<SkuImagesMapper, SkuImagesDo> implements SkuImagesRepository, IService<SkuImagesDo> {


    @Override
    public SkuImages find(SkuImagesId id) {
        SkuImagesDo skuImagesDo = this.getById(id.getId());
        if (EmptyUtil.isNull(skuImagesDo)) {
            return null;
        }
        return SkuImagesConverter.toSkuImages(skuImagesDo);
    }

    @Override
    public SkuImagesId store(SkuImages skuImages) {
        SkuImagesDo skuImagesDo = SkuImagesConverter.fromSkuImages(skuImages);
        this.saveOrUpdate(skuImagesDo);
        return new SkuImagesId(skuImagesDo.getId());
    }

    @Override
    public boolean remove(Collection<SkuImagesId> skuImagesIdList) {
        List<Long> ids = new ArrayList<>();
        skuImagesIdList.forEach(skuImagesId -> ids.add(skuImagesId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(SkuImagesId skuImagesId) {
        return this.removeById(skuImagesId.getId());
    }

    @Override
    public Set<SkuImagesId> store(List<SkuImages> skuImagesList) {
        List<SkuImagesDo> skuImagesDoList = ListUtil.listMapCollectToList(skuImagesList, skuImages -> SkuImagesConverter.fromSkuImages(skuImages));
        this.saveOrUpdateBatch(skuImagesDoList);
        return skuImagesDoList.stream().map(skuImagesDo -> new SkuImagesId(skuImagesDo.getId())).collect(Collectors.toSet());
    }

}
