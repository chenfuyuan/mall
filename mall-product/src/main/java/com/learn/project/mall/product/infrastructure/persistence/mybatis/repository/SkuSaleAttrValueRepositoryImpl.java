package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValue;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValueId;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValueRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.SkuSaleAttrValueConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SkuSaleAttrValueDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.SkuSaleAttrValueMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * sku销售属性&值-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Repository
public class SkuSaleAttrValueRepositoryImpl extends ServiceImpl<SkuSaleAttrValueMapper, SkuSaleAttrValueDo> implements SkuSaleAttrValueRepository, IService<SkuSaleAttrValueDo> {


    @Override
    public SkuSaleAttrValue find(SkuSaleAttrValueId id) {
        SkuSaleAttrValueDo skuSaleAttrValueDo = this.getById(id.getId());
        if (EmptyUtil.isNull(skuSaleAttrValueDo)) {
            return null;
        }
        return SkuSaleAttrValueConverter.toSkuSaleAttrValue(skuSaleAttrValueDo);
    }

    @Override
    public SkuSaleAttrValueId store(SkuSaleAttrValue skuSaleAttrValue) {
        SkuSaleAttrValueDo skuSaleAttrValueDo = SkuSaleAttrValueConverter.fromSkuSaleAttrValue(skuSaleAttrValue);
        this.saveOrUpdate(skuSaleAttrValueDo);
        return new SkuSaleAttrValueId(skuSaleAttrValueDo.getId());
    }

    @Override
    public boolean remove(Collection<SkuSaleAttrValueId> skuSaleAttrValueIdList) {
        List<Long> ids = new ArrayList<>();
        skuSaleAttrValueIdList.forEach(skuSaleAttrValueId -> ids.add(skuSaleAttrValueId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(SkuSaleAttrValueId skuSaleAttrValueId) {
        return this.removeById(skuSaleAttrValueId.getId());
    }

    @Override
    public Set<SkuSaleAttrValueId> store(List<SkuSaleAttrValue> skuSaleAttrValueList) {
        List<SkuSaleAttrValueDo> skuSaleAttrValueDoList = ListUtil.listMapCollectToList(skuSaleAttrValueList, skuSaleAttrValue -> SkuSaleAttrValueConverter.fromSkuSaleAttrValue(skuSaleAttrValue));
        this.saveOrUpdateBatch(skuSaleAttrValueDoList);
        return skuSaleAttrValueDoList.stream().map(skuSaleAttrValueDo -> new SkuSaleAttrValueId(skuSaleAttrValueDo.getId())).collect(Collectors.toSet());
    }

}
