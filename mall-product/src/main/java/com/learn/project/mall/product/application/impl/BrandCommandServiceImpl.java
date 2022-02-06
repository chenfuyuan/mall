package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.BrandCommandService;
import com.learn.project.mall.product.application.assembler.BrandAssembler;
import com.learn.project.mall.product.application.command.BrandCommand;
import com.learn.project.mall.product.domain.model.brand.Brand;
import com.learn.project.mall.product.domain.model.brand.BrandId;
import com.learn.project.mall.product.domain.model.brand.BrandRepository;
import com.learn.project.mall.product.domain.specification.BrandCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 品牌-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
@Service("brandCommandService")
public class BrandCommandServiceImpl implements BrandCommandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandCreateSpecification brandCreateSpecification;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(BrandCommand brandCommand) {
        Brand brand = BrandAssembler.toBrand(brandCommand);
        brandCreateSpecification.isSatisfiedBy(brand);
        return brandRepository.store(brand).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> brandIds) {
        if (EmptyUtil.isEmpty(brandIds)) {
            return false;
        }

        List<BrandId> brandIdList = new ArrayList<>();
        for (Long id : brandIds) {
            brandIdList.add(new BrandId(id));
        }
        return brandRepository.remove(brandIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long brandId) {
        return brandRepository.remove(new BrandId(brandId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<BrandCommand> brandCommandList) {
        List<Brand> brandList = ListUtil.listMapCollectToList(brandCommandList, command ->
                BrandAssembler.toBrand(command)
        );

        //校验
        brandList.forEach(brand->{
            brandCreateSpecification.isSatisfiedBy(brand);
        });

        return brandRepository.store(brandList).stream().map(brandId->brandId.getId()).toArray(Long[]::new);
    }
}
