package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.AttrGroupQueryService;
import com.learn.project.mall.product.application.assembler.AttrGroupAssembler;
import com.learn.project.mall.product.application.dto.AttrGroupDto;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroup;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroupId;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroupRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.AttrGroupDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.AttrGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 属性分组-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Service("attrGroupQueryService")
public class AttrGroupQueryServiceImpl implements AttrGroupQueryService {

    @Autowired
    private AttrGroupMapper attrGroupMapper;

    @Autowired
    private AttrGroupRepository attrGroupRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<AttrGroupDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<AttrGroupDo> page = attrGroupMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public AttrGroupDto getById(Long attrGroupId) {
        AttrGroup attrGroup = attrGroupRepository.find(new AttrGroupId(attrGroupId));
        return AttrGroupAssembler.fromAttrGroup(attrGroup);
    }


}
