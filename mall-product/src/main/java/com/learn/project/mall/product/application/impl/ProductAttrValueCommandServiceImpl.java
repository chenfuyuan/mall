package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.ProductAttrValueCommandService;
import com.learn.project.mall.product.application.assembler.ProductAttrValueAssembler;
import com.learn.project.mall.product.application.command.ProductAttrValueCommand;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValue;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValueId;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValueRepository;
import com.learn.project.mall.product.domain.specification.ProductAttrValueCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * spu属性值-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("productAttrValueCommandService")
public class ProductAttrValueCommandServiceImpl implements ProductAttrValueCommandService {

    @Autowired
    private ProductAttrValueRepository productAttrValueRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(ProductAttrValueCommand productAttrValueCommand) {
        ProductAttrValue productAttrValue = ProductAttrValueAssembler.toProductAttrValue(productAttrValueCommand);
        ProductAttrValueCreateSpecification productAttrValueCreateSpecification = new ProductAttrValueCreateSpecification();
        productAttrValueCreateSpecification.isSatisfiedBy(productAttrValue);
        return productAttrValueRepository.store(productAttrValue).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> ids) {
        if (EmptyUtil.isEmpty(ids)) {
            return false;
        }

        List<ProductAttrValueId> productAttrValueIdList = new ArrayList<>();
        for (Long id : ids) {
            productAttrValueIdList.add(new ProductAttrValueId(id));
        }
        return productAttrValueRepository.remove(productAttrValueIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return productAttrValueRepository.remove(new ProductAttrValueId(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<ProductAttrValueCommand> productAttrValueCommandList) {
        List<ProductAttrValue> productAttrValueList = ListUtil.listMapCollectToList(productAttrValueCommandList, command ->
                ProductAttrValueAssembler.toProductAttrValue(command)
        );

        //校验
        ProductAttrValueCreateSpecification productAttrValueCreateSpecification = new ProductAttrValueCreateSpecification();
        productAttrValueList.forEach(productAttrValue->{
            productAttrValueCreateSpecification.isSatisfiedBy(productAttrValue);
        });

        return productAttrValueRepository.store(productAttrValueList).stream().map(id->id.getId()).toArray(Long[]::new);
    }
}
