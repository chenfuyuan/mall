package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.SpuInfoCommandService;
import com.learn.project.mall.product.application.assembler.SpuInfoAssembler;
import com.learn.project.mall.product.application.command.SpuInfoCommand;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfo;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfoId;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfoRepository;
import com.learn.project.mall.product.domain.specification.SpuInfoCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * spu信息-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("spuInfoCommandService")
public class SpuInfoCommandServiceImpl implements SpuInfoCommandService {

    @Autowired
    private SpuInfoRepository spuInfoRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(SpuInfoCommand spuInfoCommand) {
        SpuInfo spuInfo = SpuInfoAssembler.toSpuInfo(spuInfoCommand);
        SpuInfoCreateSpecification spuInfoCreateSpecification = new SpuInfoCreateSpecification();
        spuInfoCreateSpecification.isSatisfiedBy(spuInfo);
        return spuInfoRepository.store(spuInfo).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> ids) {
        if (EmptyUtil.isEmpty(ids)) {
            return false;
        }

        List<SpuInfoId> spuInfoIdList = new ArrayList<>();
        for (Long id : ids) {
            spuInfoIdList.add(new SpuInfoId(id));
        }
        return spuInfoRepository.remove(spuInfoIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return spuInfoRepository.remove(new SpuInfoId(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<SpuInfoCommand> spuInfoCommandList) {
        List<SpuInfo> spuInfoList = ListUtil.listMapCollectToList(spuInfoCommandList, command ->
                SpuInfoAssembler.toSpuInfo(command)
        );

        //校验
        SpuInfoCreateSpecification spuInfoCreateSpecification = new SpuInfoCreateSpecification();
        spuInfoList.forEach(spuInfo->{
            spuInfoCreateSpecification.isSatisfiedBy(spuInfo);
        });

        return spuInfoRepository.store(spuInfoList).stream().map(id->id.getId()).toArray(Long[]::new);
    }
}
