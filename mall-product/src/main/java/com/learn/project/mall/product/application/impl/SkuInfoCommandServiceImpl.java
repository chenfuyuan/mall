package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.SkuInfoCommandService;
import com.learn.project.mall.product.application.assembler.SkuInfoAssembler;
import com.learn.project.mall.product.application.command.SkuInfoCommand;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfo;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfoId;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfoRepository;
import com.learn.project.mall.product.domain.specification.SkuInfoCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * sku信息-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Service("skuInfoCommandService")
public class SkuInfoCommandServiceImpl implements SkuInfoCommandService {

    @Autowired
    private SkuInfoRepository skuInfoRepository;

    @Autowired
    private SkuInfoCreateSpecification skuInfoCreateSpecification;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(SkuInfoCommand skuInfoCommand) {
        SkuInfo skuInfo = SkuInfoAssembler.toSkuInfo(skuInfoCommand);
        skuInfoCreateSpecification.isSatisfiedBy(skuInfo);
        return skuInfoRepository.store(skuInfo).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> skuIds) {
        if (EmptyUtil.isEmpty(skuIds)) {
            return false;
        }

        List<SkuInfoId> skuInfoIdList = new ArrayList<>();
        for (Long id : skuIds) {
            skuInfoIdList.add(new SkuInfoId(id));
        }
        return skuInfoRepository.remove(skuInfoIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long skuId) {
        return skuInfoRepository.remove(new SkuInfoId(skuId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<SkuInfoCommand> skuInfoCommandList) {
        List<SkuInfo> skuInfoList = ListUtil.listMapCollectToList(skuInfoCommandList, command ->
                SkuInfoAssembler.toSkuInfo(command)
        );

        //校验
        skuInfoList.forEach(skuInfo->{
            skuInfoCreateSpecification.isSatisfiedBy(skuInfo);
        });

        return skuInfoRepository.store(skuInfoList).stream().map(skuId->skuId.getId()).toArray(Long[]::new);
    }
}
