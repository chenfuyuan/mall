package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.SkuSaleAttrValueQueryService;
import com.learn.project.mall.product.application.assembler.SkuSaleAttrValueAssembler;
import com.learn.project.mall.product.application.dto.SkuSaleAttrValueDto;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValue;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValueId;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValueRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SkuSaleAttrValueDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.SkuSaleAttrValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * sku销售属性&值-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Service("skuSaleAttrValueQueryService")
public class SkuSaleAttrValueQueryServiceImpl implements SkuSaleAttrValueQueryService {

    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Autowired
    private SkuSaleAttrValueRepository skuSaleAttrValueRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<SkuSaleAttrValueDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<SkuSaleAttrValueDo> page = skuSaleAttrValueMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public SkuSaleAttrValueDto getById(Long id) {
        SkuSaleAttrValue skuSaleAttrValue = skuSaleAttrValueRepository.find(new SkuSaleAttrValueId(id));
        return SkuSaleAttrValueAssembler.fromSkuSaleAttrValue(skuSaleAttrValue);
    }


}
