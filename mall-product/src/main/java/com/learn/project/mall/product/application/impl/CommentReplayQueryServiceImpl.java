package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.CommentReplayQueryService;
import com.learn.project.mall.product.application.assembler.CommentReplayAssembler;
import com.learn.project.mall.product.application.dto.CommentReplayDto;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplay;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplayId;
import com.learn.project.mall.product.domain.model.commentReplay.CommentReplayRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CommentReplayDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.CommentReplayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品评价回复关系-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("commentReplayQueryService")
public class CommentReplayQueryServiceImpl implements CommentReplayQueryService {

    @Autowired
    private CommentReplayMapper commentReplayMapper;

    @Autowired
    private CommentReplayRepository commentReplayRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<CommentReplayDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<CommentReplayDo> page = commentReplayMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public CommentReplayDto getById(Long id) {
        CommentReplay commentReplay = commentReplayRepository.find(new CommentReplayId(id));
        return CommentReplayAssembler.fromCommentReplay(commentReplay);
    }


}
