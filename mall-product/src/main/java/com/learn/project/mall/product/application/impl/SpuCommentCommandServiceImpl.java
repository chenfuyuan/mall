package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.SpuCommentCommandService;
import com.learn.project.mall.product.application.assembler.SpuCommentAssembler;
import com.learn.project.mall.product.application.command.SpuCommentCommand;
import com.learn.project.mall.product.domain.model.spuComment.SpuComment;
import com.learn.project.mall.product.domain.model.spuComment.SpuCommentId;
import com.learn.project.mall.product.domain.model.spuComment.SpuCommentRepository;
import com.learn.project.mall.product.domain.specification.SpuCommentCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 商品评价-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("spuCommentCommandService")
public class SpuCommentCommandServiceImpl implements SpuCommentCommandService {

    @Autowired
    private SpuCommentRepository spuCommentRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(SpuCommentCommand spuCommentCommand) {
        SpuComment spuComment = SpuCommentAssembler.toSpuComment(spuCommentCommand);
        SpuCommentCreateSpecification spuCommentCreateSpecification = new SpuCommentCreateSpecification();
        spuCommentCreateSpecification.isSatisfiedBy(spuComment);
        return spuCommentRepository.store(spuComment).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> ids) {
        if (EmptyUtil.isEmpty(ids)) {
            return false;
        }

        List<SpuCommentId> spuCommentIdList = new ArrayList<>();
        for (Long id : ids) {
            spuCommentIdList.add(new SpuCommentId(id));
        }
        return spuCommentRepository.remove(spuCommentIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return spuCommentRepository.remove(new SpuCommentId(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<SpuCommentCommand> spuCommentCommandList) {
        List<SpuComment> spuCommentList = ListUtil.listMapCollectToList(spuCommentCommandList, command ->
                SpuCommentAssembler.toSpuComment(command)
        );

        //校验
        SpuCommentCreateSpecification spuCommentCreateSpecification = new SpuCommentCreateSpecification();
        spuCommentList.forEach(spuComment->{
            spuCommentCreateSpecification.isSatisfiedBy(spuComment);
        });

        return spuCommentRepository.store(spuCommentList).stream().map(id->id.getId()).toArray(Long[]::new);
    }
}
