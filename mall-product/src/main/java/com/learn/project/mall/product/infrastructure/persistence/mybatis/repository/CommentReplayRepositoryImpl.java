package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplay;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplayId;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplayRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.CommentReplayConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CommentReplayDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.CommentReplayMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 商品评价回复关系-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Repository
public class CommentReplayRepositoryImpl extends ServiceImpl<CommentReplayMapper, CommentReplayDo> implements CommentReplayRepository, IService<CommentReplayDo> {


    @Override
    public CommentReplay find(CommentReplayId id) {
        CommentReplayDo commentReplayDo = this.getById(id.getId());
        if (EmptyUtil.isNull(commentReplayDo)) {
            return null;
        }
        return CommentReplayConverter.toCommentReplay(commentReplayDo);
    }

    @Override
    public CommentReplayId store(CommentReplay commentReplay) {
        CommentReplayDo commentReplayDo = CommentReplayConverter.fromCommentReplay(commentReplay);
        this.saveOrUpdate(commentReplayDo);
        return new CommentReplayId(commentReplayDo.getId());
    }

    @Override
    public boolean remove(Collection<CommentReplayId> commentReplayIdList) {
        List<Long> ids = new ArrayList<>();
        commentReplayIdList.forEach(commentReplayId -> ids.add(commentReplayId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(CommentReplayId commentReplayId) {
        return this.removeById(commentReplayId.getId());
    }

    @Override
    public Set<CommentReplayId> store(List<CommentReplay> commentReplayList) {
        List<CommentReplayDo> commentReplayDoList = ListUtil.listMapCollectToList(commentReplayList, commentReplay -> CommentReplayConverter.fromCommentReplay(commentReplay));
        this.saveOrUpdateBatch(commentReplayDoList);
        return commentReplayDoList.stream().map(commentReplayDo -> new CommentReplayId(commentReplayDo.getId())).collect(Collectors.toSet());
    }

}
