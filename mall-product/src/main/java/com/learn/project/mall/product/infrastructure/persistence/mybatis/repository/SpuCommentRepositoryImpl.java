package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.spuComment.SpuComment;
import com.learn.project.mall.product.domain.model.spuComment.SpuCommentId;
import com.learn.project.mall.product.domain.model.spuComment.SpuCommentRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.SpuCommentConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SpuCommentDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.SpuCommentMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 商品评价-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Repository
public class SpuCommentRepositoryImpl extends ServiceImpl<SpuCommentMapper, SpuCommentDo> implements SpuCommentRepository, IService<SpuCommentDo> {


    @Override
    public SpuComment find(SpuCommentId id) {
        SpuCommentDo spuCommentDo = this.getById(id.getId());
        if (EmptyUtil.isNull(spuCommentDo)) {
            return null;
        }
        return SpuCommentConverter.toSpuComment(spuCommentDo);
    }

    @Override
    public SpuCommentId store(SpuComment spuComment) {
        SpuCommentDo spuCommentDo = SpuCommentConverter.fromSpuComment(spuComment);
        this.saveOrUpdate(spuCommentDo);
        return new SpuCommentId(spuCommentDo.getId());
    }

    @Override
    public boolean remove(Collection<SpuCommentId> spuCommentIdList) {
        List<Long> ids = new ArrayList<>();
        spuCommentIdList.forEach(spuCommentId -> ids.add(spuCommentId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(SpuCommentId spuCommentId) {
        return this.removeById(spuCommentId.getId());
    }

    @Override
    public Set<SpuCommentId> store(List<SpuComment> spuCommentList) {
        List<SpuCommentDo> spuCommentDoList = ListUtil.listMapCollectToList(spuCommentList, spuComment -> SpuCommentConverter.fromSpuComment(spuComment));
        this.saveOrUpdateBatch(spuCommentDoList);
        return spuCommentDoList.stream().map(spuCommentDo -> new SpuCommentId(spuCommentDo.getId())).collect(Collectors.toSet());
    }

}
