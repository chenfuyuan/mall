package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.ProductAttrValueQueryService;
import com.learn.project.mall.product.application.assembler.ProductAttrValueAssembler;
import com.learn.project.mall.product.application.dto.ProductAttrValueDto;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValue;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValueId;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValueRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.ProductAttrValueDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.ProductAttrValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * spu属性值-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("productAttrValueQueryService")
public class ProductAttrValueQueryServiceImpl implements ProductAttrValueQueryService {

    @Autowired
    private ProductAttrValueMapper productAttrValueMapper;

    @Autowired
    private ProductAttrValueRepository productAttrValueRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<ProductAttrValueDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<ProductAttrValueDo> page = productAttrValueMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public ProductAttrValueDto getById(Long id) {
        ProductAttrValue productAttrValue = productAttrValueRepository.find(new ProductAttrValueId(id));
        return ProductAttrValueAssembler.fromProductAttrValue(productAttrValue);
    }


}
