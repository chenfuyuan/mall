package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.CommentReplayCommandService;
import com.learn.project.mall.product.application.assembler.CommentReplayAssembler;
import com.learn.project.mall.product.application.command.CommentReplayCommand;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplay;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplayId;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplayRepository;
import com.learn.project.mall.product.domain.specification.CommentReplayCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 商品评价回复关系-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Service("commentReplayCommandService")
public class CommentReplayCommandServiceImpl implements CommentReplayCommandService {

    @Autowired
    private CommentReplayRepository commentReplayRepository;

    @Autowired
    private CommentReplayCreateSpecification commentReplayCreateSpecification;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(CommentReplayCommand commentReplayCommand) {
        CommentReplay commentReplay = CommentReplayAssembler.toCommentReplay(commentReplayCommand);
        commentReplayCreateSpecification.isSatisfiedBy(commentReplay);
        return commentReplayRepository.store(commentReplay).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> ids) {
        if (EmptyUtil.isEmpty(ids)) {
            return false;
        }

        List<CommentReplayId> commentReplayIdList = new ArrayList<>();
        for (Long id : ids) {
            commentReplayIdList.add(new CommentReplayId(id));
        }
        return commentReplayRepository.remove(commentReplayIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return commentReplayRepository.remove(new CommentReplayId(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<CommentReplayCommand> commentReplayCommandList) {
        List<CommentReplay> commentReplayList = ListUtil.listMapCollectToList(commentReplayCommandList, command ->
                CommentReplayAssembler.toCommentReplay(command)
        );

        //校验
        commentReplayList.forEach(commentReplay->{
            commentReplayCreateSpecification.isSatisfiedBy(commentReplay);
        });

        return commentReplayRepository.store(commentReplayList).stream().map(id->id.getId()).toArray(Long[]::new);
    }
}
