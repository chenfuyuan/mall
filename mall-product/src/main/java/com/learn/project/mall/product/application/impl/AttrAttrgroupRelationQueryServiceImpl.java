package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.AttrAttrgroupRelationQueryService;
import com.learn.project.mall.product.application.assembler.AttrAttrgroupRelationAssembler;
import com.learn.project.mall.product.application.dto.AttrAttrgroupRelationDto;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelation;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelationId;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelationRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.AttrAttrgroupRelationDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.AttrAttrgroupRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 属性&属性分组关联-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Service("attrAttrgroupRelationQueryService")
public class AttrAttrgroupRelationQueryServiceImpl implements AttrAttrgroupRelationQueryService {

    @Autowired
    private AttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

    @Autowired
    private AttrAttrgroupRelationRepository attrAttrgroupRelationRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<AttrAttrgroupRelationDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<AttrAttrgroupRelationDo> page = attrAttrgroupRelationMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public AttrAttrgroupRelationDto getById(Long id) {
        AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationRepository.find(new AttrAttrgroupRelationId(id));
        return AttrAttrgroupRelationAssembler.fromAttrAttrgroupRelation(attrAttrgroupRelation);
    }


}
