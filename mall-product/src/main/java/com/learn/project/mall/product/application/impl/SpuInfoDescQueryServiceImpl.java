package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.SpuInfoDescQueryService;
import com.learn.project.mall.product.application.assembler.SpuInfoDescAssembler;
import com.learn.project.mall.product.application.dto.SpuInfoDescDto;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDesc;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDescId;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDescRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SpuInfoDescDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.SpuInfoDescMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * spu信息介绍-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Service("spuInfoDescQueryService")
public class SpuInfoDescQueryServiceImpl implements SpuInfoDescQueryService {

    @Autowired
    private SpuInfoDescMapper spuInfoDescMapper;

    @Autowired
    private SpuInfoDescRepository spuInfoDescRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<SpuInfoDescDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<SpuInfoDescDo> page = spuInfoDescMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public SpuInfoDescDto getById(Long spuId) {
        SpuInfoDesc spuInfoDesc = spuInfoDescRepository.find(new SpuInfoDescId(spuId));
        return SpuInfoDescAssembler.fromSpuInfoDesc(spuInfoDesc);
    }


}
