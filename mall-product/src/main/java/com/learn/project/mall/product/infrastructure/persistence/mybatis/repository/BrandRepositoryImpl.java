package com.learn.project.mall.product.infrastructure.persistence.mybatis.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.project.mall.product.domain.model.brand.Brand;
import com.learn.project.mall.product.domain.model.brand.BrandId;
import com.learn.project.mall.product.domain.model.brand.BrandRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.converter.BrandConverter;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.BrandDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.BrandMapper;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 品牌-仓储服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Repository
public class BrandRepositoryImpl extends ServiceImpl<BrandMapper, BrandDo> implements BrandRepository, IService<BrandDo> {


    @Override
    public Brand find(BrandId brandId) {
        BrandDo brandDo = this.getById(brandId.getId());
        if (EmptyUtil.isNull(brandDo)) {
            return null;
        }
        return BrandConverter.toBrand(brandDo);
    }

    @Override
    public BrandId store(Brand brand) {
        BrandDo brandDo = BrandConverter.fromBrand(brand);
        this.saveOrUpdate(brandDo);
        return new BrandId(brandDo.getBrandId());
    }

    @Override
    public boolean remove(Collection<BrandId> brandIdList) {
        List<Long> ids = new ArrayList<>();
        brandIdList.forEach(brandId -> ids.add(brandId.getId()));
        return this.removeByIds(ids);
    }

    @Override
    public boolean remove(BrandId brandId) {
        return this.removeById(brandId.getId());
    }

    @Override
    public Set<BrandId> store(List<Brand> brandList) {
        List<BrandDo> brandDoList = ListUtil.listMapCollectToList(brandList, brand -> BrandConverter.fromBrand(brand));
        this.saveOrUpdateBatch(brandDoList);
        return brandDoList.stream().map(brandDo -> new BrandId(brandDo.getBrandId())).collect(Collectors.toSet());
    }

}
