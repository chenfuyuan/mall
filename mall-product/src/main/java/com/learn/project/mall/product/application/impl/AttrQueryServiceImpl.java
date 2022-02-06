package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.AttrQueryService;
import com.learn.project.mall.product.application.assembler.AttrAssembler;
import com.learn.project.mall.product.application.dto.AttrDto;
import com.learn.project.mall.product.domain.model.attr.Attr;
import com.learn.project.mall.product.domain.model.attr.AttrId;
import com.learn.project.mall.product.domain.model.attr.AttrRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.AttrDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.AttrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品属性-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("attrQueryService")
public class AttrQueryServiceImpl implements AttrQueryService {

    @Autowired
    private AttrMapper attrMapper;

    @Autowired
    private AttrRepository attrRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<AttrDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<AttrDo> page = attrMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public AttrDto getById(Long attrId) {
        Attr attr = attrRepository.find(new AttrId(attrId));
        return AttrAssembler.fromAttr(attr);
    }


}
