package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.AttrAttrgroupRelationCommandService;
import com.learn.project.mall.product.application.assembler.AttrAttrgroupRelationAssembler;
import com.learn.project.mall.product.application.command.AttrAttrgroupRelationCommand;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelation;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelationId;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelationRepository;
import com.learn.project.mall.product.domain.specification.AttrAttrgroupRelationCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 属性&属性分组关联-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("attrAttrgroupRelationCommandService")
public class AttrAttrgroupRelationCommandServiceImpl implements AttrAttrgroupRelationCommandService {

    @Autowired
    private AttrAttrgroupRelationRepository attrAttrgroupRelationRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(AttrAttrgroupRelationCommand attrAttrgroupRelationCommand) {
        AttrAttrgroupRelation attrAttrgroupRelation = AttrAttrgroupRelationAssembler.toAttrAttrgroupRelation(attrAttrgroupRelationCommand);
        AttrAttrgroupRelationCreateSpecification attrAttrgroupRelationCreateSpecification = new AttrAttrgroupRelationCreateSpecification();
        attrAttrgroupRelationCreateSpecification.isSatisfiedBy(attrAttrgroupRelation);
        return attrAttrgroupRelationRepository.store(attrAttrgroupRelation).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> ids) {
        if (EmptyUtil.isEmpty(ids)) {
            return false;
        }

        List<AttrAttrgroupRelationId> attrAttrgroupRelationIdList = new ArrayList<>();
        for (Long id : ids) {
            attrAttrgroupRelationIdList.add(new AttrAttrgroupRelationId(id));
        }
        return attrAttrgroupRelationRepository.remove(attrAttrgroupRelationIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return attrAttrgroupRelationRepository.remove(new AttrAttrgroupRelationId(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<AttrAttrgroupRelationCommand> attrAttrgroupRelationCommandList) {
        List<AttrAttrgroupRelation> attrAttrgroupRelationList = ListUtil.listMapCollectToList(attrAttrgroupRelationCommandList, command ->
                AttrAttrgroupRelationAssembler.toAttrAttrgroupRelation(command)
        );

        //校验
        AttrAttrgroupRelationCreateSpecification attrAttrgroupRelationCreateSpecification = new AttrAttrgroupRelationCreateSpecification();
        attrAttrgroupRelationList.forEach(attrAttrgroupRelation->{
            attrAttrgroupRelationCreateSpecification.isSatisfiedBy(attrAttrgroupRelation);
        });

        return attrAttrgroupRelationRepository.store(attrAttrgroupRelationList).stream().map(id->id.getId()).toArray(Long[]::new);
    }
}
