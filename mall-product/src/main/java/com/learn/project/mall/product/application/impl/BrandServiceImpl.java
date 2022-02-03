package com.learn.project.mall.product.application.impl;

import com.learn.project.common.mybatis.util.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.common.mybatis.util.PageUtils;


import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.BrandDao;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.BrandEntity;
import com.learn.project.mall.product.application.BrandService;

/**
 * 品牌Service实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2021-12-12 14:35:18
 */
@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                new QueryWrapper<BrandEntity>()
        );

        return new PageUtils(page);
    }

}