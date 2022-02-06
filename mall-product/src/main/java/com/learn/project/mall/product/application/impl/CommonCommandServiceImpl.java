package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.CommonCommandService;
import com.learn.project.mall.product.application.assembler.CommonAssembler;
import com.learn.project.mall.product.application.command.CommonCommand;
import com.learn.project.mall.product.domain.model.common.Common;
import com.learn.project.mall.product.domain.model.common.CommonId;
import com.learn.project.mall.product.domain.model.common.CommonRepository;
import com.learn.project.mall.product.domain.specification.CommonCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 通用测试-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Service("commonCommandService")
public class CommonCommandServiceImpl implements CommonCommandService {

    @Autowired
    private CommonRepository commonRepository;

    @Autowired
    private CommonCreateSpecification commonCreateSpecification;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(CommonCommand commonCommand) {
        Common common = CommonAssembler.toCommon(commonCommand);
        commonCreateSpecification.isSatisfiedBy(common);
        return commonRepository.store(common).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> commonIds) {
        if (EmptyUtil.isEmpty(commonIds)) {
            return false;
        }

        List<CommonId> commonIdList = new ArrayList<>();
        for (Long id : commonIds) {
            commonIdList.add(new CommonId(id));
        }
        return commonRepository.remove(commonIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long commonId) {
        return commonRepository.remove(new CommonId(commonId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<CommonCommand> commonCommandList) {
        List<Common> commonList = ListUtil.listMapCollectToList(commonCommandList, command ->
                CommonAssembler.toCommon(command)
        );

        //校验
        commonList.forEach(common->{
            commonCreateSpecification.isSatisfiedBy(common);
        });

        return commonRepository.store(commonList).stream().map(commonId->commonId.getId()).toArray(Long[]::new);
    }
}
