package com.learn.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.common.utils.PageUtils;
import com.learn.common.utils.Query;
import com.learn.gulimall.product.dao.BrandDao;
import com.learn.gulimall.product.entity.BrandEntity;
import com.learn.gulimall.product.service.BrandService;
import com.learn.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                new QueryWrapper<BrandEntity>().like("name", params.get("key"))
        );
        
        return new PageUtils(page);
    }

    /**
     * 更新品牌详情，并更新品牌关联分类信息
     * @author: Vito.Chen
     * @date: 2020-8-13 22:38
     * @param brand
     * @return: void
     */
    @Override
    public void updateDetail(BrandEntity brand) {
        //更新品牌信息
        updateById(brand);

        //更新品牌关联字段
        categoryBrandRelationService.updateBrand(brand.getBrandId(),brand.getName());
    }
}