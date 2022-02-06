package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.AttrCommandService;
import com.learn.project.mall.product.application.assembler.AttrAssembler;
import com.learn.project.mall.product.application.command.AttrCommand;
import com.learn.project.mall.product.domain.model.attr.Attr;
import com.learn.project.mall.product.domain.model.attr.AttrId;
import com.learn.project.mall.product.domain.model.attr.AttrRepository;
import com.learn.project.mall.product.domain.specification.AttrCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 商品属性-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("attrCommandService")
public class AttrCommandServiceImpl implements AttrCommandService {

    @Autowired
    private AttrRepository attrRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(AttrCommand attrCommand) {
        Attr attr = AttrAssembler.toAttr(attrCommand);
        AttrCreateSpecification attrCreateSpecification = new AttrCreateSpecification();
        attrCreateSpecification.isSatisfiedBy(attr);
        return attrRepository.store(attr).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> attrIds) {
        if (EmptyUtil.isEmpty(attrIds)) {
            return false;
        }

        List<AttrId> attrIdList = new ArrayList<>();
        for (Long id : attrIds) {
            attrIdList.add(new AttrId(id));
        }
        return attrRepository.remove(attrIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long attrId) {
        return attrRepository.remove(new AttrId(attrId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<AttrCommand> attrCommandList) {
        List<Attr> attrList = ListUtil.listMapCollectToList(attrCommandList, command ->
                AttrAssembler.toAttr(command)
        );

        //校验
        AttrCreateSpecification attrCreateSpecification = new AttrCreateSpecification();
        attrList.forEach(attr->{
            attrCreateSpecification.isSatisfiedBy(attr);
        });

        return attrRepository.store(attrList).stream().map(attrId->attrId.getId()).toArray(Long[]::new);
    }
}
