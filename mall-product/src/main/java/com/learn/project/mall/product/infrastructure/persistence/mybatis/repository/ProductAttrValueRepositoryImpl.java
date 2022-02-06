package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValue;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValueId;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValueRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.ProductAttrValueConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.ProductAttrValueDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.ProductAttrValueMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * spu属性值-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Repository
public class ProductAttrValueRepositoryImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValueDo> implements ProductAttrValueRepository, IService<ProductAttrValueDo> {


    @Override
    public ProductAttrValue find(ProductAttrValueId id) {
        ProductAttrValueDo productAttrValueDo = this.getById(id.getId());
        if (EmptyUtil.isNull(productAttrValueDo)) {
            return null;
        }
        return ProductAttrValueConverter.toProductAttrValue(productAttrValueDo);
    }

    @Override
    public ProductAttrValueId store(ProductAttrValue productAttrValue) {
        ProductAttrValueDo productAttrValueDo = ProductAttrValueConverter.fromProductAttrValue(productAttrValue);
        this.saveOrUpdate(productAttrValueDo);
        return new ProductAttrValueId(productAttrValueDo.getId());
    }

    @Override
    public boolean remove(Collection<ProductAttrValueId> productAttrValueIdList) {
        List<Long> ids = new ArrayList<>();
        productAttrValueIdList.forEach(productAttrValueId -> ids.add(productAttrValueId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(ProductAttrValueId productAttrValueId) {
        return this.removeById(productAttrValueId.getId());
    }

    @Override
    public Set<ProductAttrValueId> store(List<ProductAttrValue> productAttrValueList) {
        List<ProductAttrValueDo> productAttrValueDoList = ListUtil.listMapCollectToList(productAttrValueList, productAttrValue -> ProductAttrValueConverter.fromProductAttrValue(productAttrValue));
        this.saveOrUpdateBatch(productAttrValueDoList);
        return productAttrValueDoList.stream().map(productAttrValueDo -> new ProductAttrValueId(productAttrValueDo.getId())).collect(Collectors.toSet());
    }

}
