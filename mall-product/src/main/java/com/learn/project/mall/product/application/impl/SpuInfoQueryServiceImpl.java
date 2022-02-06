package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.SpuInfoQueryService;
import com.learn.project.mall.product.application.assembler.SpuInfoAssembler;
import com.learn.project.mall.product.application.dto.SpuInfoDto;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfo;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfoId;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfoRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SpuInfoDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.SpuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * spu信息-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Service("spuInfoQueryService")
public class SpuInfoQueryServiceImpl implements SpuInfoQueryService {

    @Autowired
    private SpuInfoMapper spuInfoMapper;

    @Autowired
    private SpuInfoRepository spuInfoRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<SpuInfoDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<SpuInfoDo> page = spuInfoMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public SpuInfoDto getById(Long id) {
        SpuInfo spuInfo = spuInfoRepository.find(new SpuInfoId(id));
        return SpuInfoAssembler.fromSpuInfo(spuInfo);
    }


}
