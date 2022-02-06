package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.BrandQueryService;
import com.learn.project.mall.product.application.assembler.BrandAssembler;
import com.learn.project.mall.product.application.dto.BrandDto;
import com.learn.project.mall.product.domain.model.brand.Brand;
import com.learn.project.mall.product.domain.model.brand.BrandId;
import com.learn.project.mall.product.domain.model.brand.BrandRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.BrandDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 品牌-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("brandQueryService")
public class BrandQueryServiceImpl implements BrandQueryService {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<BrandDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<BrandDo> page = brandMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public BrandDto getById(Long brandId) {
        Brand brand = brandRepository.find(new BrandId(brandId));
        return BrandAssembler.fromBrand(brand);
    }


}
