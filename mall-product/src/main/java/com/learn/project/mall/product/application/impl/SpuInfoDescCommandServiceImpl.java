package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.SpuInfoDescCommandService;
import com.learn.project.mall.product.application.assembler.SpuInfoDescAssembler;
import com.learn.project.mall.product.application.command.SpuInfoDescCommand;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDesc;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDescId;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDescRepository;
import com.learn.project.mall.product.domain.specification.SpuInfoDescCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * spu信息介绍-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Service("spuInfoDescCommandService")
public class SpuInfoDescCommandServiceImpl implements SpuInfoDescCommandService {

    @Autowired
    private SpuInfoDescRepository spuInfoDescRepository;

    @Autowired
    private SpuInfoDescCreateSpecification spuInfoDescCreateSpecification;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(SpuInfoDescCommand spuInfoDescCommand) {
        SpuInfoDesc spuInfoDesc = SpuInfoDescAssembler.toSpuInfoDesc(spuInfoDescCommand);
        spuInfoDescCreateSpecification.isSatisfiedBy(spuInfoDesc);
        return spuInfoDescRepository.store(spuInfoDesc).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> spuIds) {
        if (EmptyUtil.isEmpty(spuIds)) {
            return false;
        }

        List<SpuInfoDescId> spuInfoDescIdList = new ArrayList<>();
        for (Long id : spuIds) {
            spuInfoDescIdList.add(new SpuInfoDescId(id));
        }
        return spuInfoDescRepository.remove(spuInfoDescIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long spuId) {
        return spuInfoDescRepository.remove(new SpuInfoDescId(spuId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<SpuInfoDescCommand> spuInfoDescCommandList) {
        List<SpuInfoDesc> spuInfoDescList = ListUtil.listMapCollectToList(spuInfoDescCommandList, command ->
                SpuInfoDescAssembler.toSpuInfoDesc(command)
        );

        //校验
        spuInfoDescList.forEach(spuInfoDesc->{
            spuInfoDescCreateSpecification.isSatisfiedBy(spuInfoDesc);
        });

        return spuInfoDescRepository.store(spuInfoDescList).stream().map(spuId->spuId.getId()).toArray(Long[]::new);
    }
}
