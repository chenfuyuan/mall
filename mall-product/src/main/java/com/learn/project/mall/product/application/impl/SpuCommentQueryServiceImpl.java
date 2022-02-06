package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.SpuCommentQueryService;
import com.learn.project.mall.product.application.assembler.SpuCommentAssembler;
import com.learn.project.mall.product.application.dto.SpuCommentDto;
import com.learn.project.mall.product.domain.model.spuComment.SpuComment;
import com.learn.project.mall.product.domain.model.spuComment.SpuCommentId;
import com.learn.project.mall.product.domain.model.spuComment.SpuCommentRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SpuCommentDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.SpuCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品评价-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Service("spuCommentQueryService")
public class SpuCommentQueryServiceImpl implements SpuCommentQueryService {

    @Autowired
    private SpuCommentMapper spuCommentMapper;

    @Autowired
    private SpuCommentRepository spuCommentRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<SpuCommentDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<SpuCommentDo> page = spuCommentMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public SpuCommentDto getById(Long id) {
        SpuComment spuComment = spuCommentRepository.find(new SpuCommentId(id));
        return SpuCommentAssembler.fromSpuComment(spuComment);
    }


}
