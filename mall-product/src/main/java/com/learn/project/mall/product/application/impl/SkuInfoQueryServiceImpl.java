package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.SkuInfoQueryService;
import com.learn.project.mall.product.application.assembler.SkuInfoAssembler;
import com.learn.project.mall.product.application.dto.SkuInfoDto;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfo;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfoId;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfoRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SkuInfoDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.SkuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * sku信息-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Service("skuInfoQueryService")
public class SkuInfoQueryServiceImpl implements SkuInfoQueryService {

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired
    private SkuInfoRepository skuInfoRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<SkuInfoDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<SkuInfoDo> page = skuInfoMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public SkuInfoDto getById(Long skuId) {
        SkuInfo skuInfo = skuInfoRepository.find(new SkuInfoId(skuId));
        return SkuInfoAssembler.fromSkuInfo(skuInfo);
    }


}
