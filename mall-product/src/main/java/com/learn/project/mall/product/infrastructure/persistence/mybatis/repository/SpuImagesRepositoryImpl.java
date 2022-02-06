package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.spuImages.SpuImages;
import com.learn.project.mall.product.domain.model.spuImages.SpuImagesId;
import com.learn.project.mall.product.domain.model.spuImages.SpuImagesRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.SpuImagesConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SpuImagesDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.SpuImagesMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * spu图片-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Repository
public class SpuImagesRepositoryImpl extends ServiceImpl<SpuImagesMapper, SpuImagesDo> implements SpuImagesRepository, IService<SpuImagesDo> {


    @Override
    public SpuImages find(SpuImagesId id) {
        SpuImagesDo spuImagesDo = this.getById(id.getId());
        if (EmptyUtil.isNull(spuImagesDo)) {
            return null;
        }
        return SpuImagesConverter.toSpuImages(spuImagesDo);
    }

    @Override
    public SpuImagesId store(SpuImages spuImages) {
        SpuImagesDo spuImagesDo = SpuImagesConverter.fromSpuImages(spuImages);
        this.saveOrUpdate(spuImagesDo);
        return new SpuImagesId(spuImagesDo.getId());
    }

    @Override
    public boolean remove(Collection<SpuImagesId> spuImagesIdList) {
        List<Long> ids = new ArrayList<>();
        spuImagesIdList.forEach(spuImagesId -> ids.add(spuImagesId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(SpuImagesId spuImagesId) {
        return this.removeById(spuImagesId.getId());
    }

    @Override
    public Set<SpuImagesId> store(List<SpuImages> spuImagesList) {
        List<SpuImagesDo> spuImagesDoList = ListUtil.listMapCollectToList(spuImagesList, spuImages -> SpuImagesConverter.fromSpuImages(spuImages));
        this.saveOrUpdateBatch(spuImagesDoList);
        return spuImagesDoList.stream().map(spuImagesDo -> new SpuImagesId(spuImagesDo.getId())).collect(Collectors.toSet());
    }

}
