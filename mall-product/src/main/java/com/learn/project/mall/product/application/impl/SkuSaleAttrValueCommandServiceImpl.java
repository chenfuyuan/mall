package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.SkuSaleAttrValueCommandService;
import com.learn.project.mall.product.application.assembler.SkuSaleAttrValueAssembler;
import com.learn.project.mall.product.application.command.SkuSaleAttrValueCommand;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValue;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValueId;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValueRepository;
import com.learn.project.mall.product.domain.specification.SkuSaleAttrValueCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * sku销售属性&值-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("skuSaleAttrValueCommandService")
public class SkuSaleAttrValueCommandServiceImpl implements SkuSaleAttrValueCommandService {

    @Autowired
    private SkuSaleAttrValueRepository skuSaleAttrValueRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(SkuSaleAttrValueCommand skuSaleAttrValueCommand) {
        SkuSaleAttrValue skuSaleAttrValue = SkuSaleAttrValueAssembler.toSkuSaleAttrValue(skuSaleAttrValueCommand);
        SkuSaleAttrValueCreateSpecification skuSaleAttrValueCreateSpecification = new SkuSaleAttrValueCreateSpecification();
        skuSaleAttrValueCreateSpecification.isSatisfiedBy(skuSaleAttrValue);
        return skuSaleAttrValueRepository.store(skuSaleAttrValue).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> ids) {
        if (EmptyUtil.isEmpty(ids)) {
            return false;
        }

        List<SkuSaleAttrValueId> skuSaleAttrValueIdList = new ArrayList<>();
        for (Long id : ids) {
            skuSaleAttrValueIdList.add(new SkuSaleAttrValueId(id));
        }
        return skuSaleAttrValueRepository.remove(skuSaleAttrValueIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return skuSaleAttrValueRepository.remove(new SkuSaleAttrValueId(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<SkuSaleAttrValueCommand> skuSaleAttrValueCommandList) {
        List<SkuSaleAttrValue> skuSaleAttrValueList = ListUtil.listMapCollectToList(skuSaleAttrValueCommandList, command ->
                SkuSaleAttrValueAssembler.toSkuSaleAttrValue(command)
        );

        //校验
        SkuSaleAttrValueCreateSpecification skuSaleAttrValueCreateSpecification = new SkuSaleAttrValueCreateSpecification();
        skuSaleAttrValueList.forEach(skuSaleAttrValue->{
            skuSaleAttrValueCreateSpecification.isSatisfiedBy(skuSaleAttrValue);
        });

        return skuSaleAttrValueRepository.store(skuSaleAttrValueList).stream().map(id->id.getId()).toArray(Long[]::new);
    }
}
