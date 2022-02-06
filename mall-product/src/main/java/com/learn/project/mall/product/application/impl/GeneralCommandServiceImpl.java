package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.GeneralCommandService;
import com.learn.project.mall.product.application.assembler.GeneralAssembler;
import com.learn.project.mall.product.application.command.GeneralCommand;
import com.learn.project.mall.product.domain.model.general.General;
import com.learn.project.mall.product.domain.model.general.GeneralId;
import com.learn.project.mall.product.domain.model.general.GeneralRepository;
import com.learn.project.mall.product.domain.specification.GeneralCreateSpecification;
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
@Service("generalCommandService")
public class GeneralCommandServiceImpl implements GeneralCommandService {

    @Autowired
    private GeneralRepository generalRepository;

    @Autowired
    private GeneralCreateSpecification generalCreateSpecification;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(GeneralCommand generalCommand) {
        General general = GeneralAssembler.toGeneral(generalCommand);
        generalCreateSpecification.isSatisfiedBy(general);
        return generalRepository.store(general).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> ids) {
        if (EmptyUtil.isEmpty(ids)) {
            return false;
        }

        List<GeneralId> generalIdList = new ArrayList<>();
        for (Long id : ids) {
            generalIdList.add(new GeneralId(id));
        }
        return generalRepository.remove(generalIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return generalRepository.remove(new GeneralId(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<GeneralCommand> generalCommandList) {
        List<General> generalList = ListUtil.listMapCollectToList(generalCommandList, command ->
                GeneralAssembler.toGeneral(command)
        );

        //校验
        generalList.forEach(general->{
            generalCreateSpecification.isSatisfiedBy(general);
        });

        return generalRepository.store(generalList).stream().map(id->id.getId()).toArray(Long[]::new);
    }
}
