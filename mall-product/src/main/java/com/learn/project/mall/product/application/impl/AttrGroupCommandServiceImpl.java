package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.AttrGroupCommandService;
import com.learn.project.mall.product.application.assembler.AttrGroupAssembler;
import com.learn.project.mall.product.application.command.AttrGroupCommand;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroup;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroupId;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroupRepository;
import com.learn.project.mall.product.domain.specification.AttrGroupCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 属性分组-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("attrGroupCommandService")
public class AttrGroupCommandServiceImpl implements AttrGroupCommandService {

    @Autowired
    private AttrGroupRepository attrGroupRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(AttrGroupCommand attrGroupCommand) {
        AttrGroup attrGroup = AttrGroupAssembler.toAttrGroup(attrGroupCommand);
        AttrGroupCreateSpecification attrGroupCreateSpecification = new AttrGroupCreateSpecification();
        attrGroupCreateSpecification.isSatisfiedBy(attrGroup);
        return attrGroupRepository.store(attrGroup).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> attrGroupIds) {
        if (EmptyUtil.isEmpty(attrGroupIds)) {
            return false;
        }

        List<AttrGroupId> attrGroupIdList = new ArrayList<>();
        for (Long id : attrGroupIds) {
            attrGroupIdList.add(new AttrGroupId(id));
        }
        return attrGroupRepository.remove(attrGroupIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long attrGroupId) {
        return attrGroupRepository.remove(new AttrGroupId(attrGroupId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<AttrGroupCommand> attrGroupCommandList) {
        List<AttrGroup> attrGroupList = ListUtil.listMapCollectToList(attrGroupCommandList, command ->
                AttrGroupAssembler.toAttrGroup(command)
        );

        //校验
        AttrGroupCreateSpecification attrGroupCreateSpecification = new AttrGroupCreateSpecification();
        attrGroupList.forEach(attrGroup->{
            attrGroupCreateSpecification.isSatisfiedBy(attrGroup);
        });

        return attrGroupRepository.store(attrGroupList).stream().map(attrGroupId->attrGroupId.getId()).toArray(Long[]::new);
    }
}
