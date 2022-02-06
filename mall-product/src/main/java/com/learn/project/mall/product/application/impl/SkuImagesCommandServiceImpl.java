package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.SkuImagesCommandService;
import com.learn.project.mall.product.application.assembler.SkuImagesAssembler;
import com.learn.project.mall.product.application.command.SkuImagesCommand;
import com.learn.project.mall.product.domain.model.skuImages.SkuImages;
import com.learn.project.mall.product.domain.model.skuImages.SkuImagesId;
import com.learn.project.mall.product.domain.model.skuImages.SkuImagesRepository;
import com.learn.project.mall.product.domain.specification.SkuImagesCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * sku图片-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("skuImagesCommandService")
public class SkuImagesCommandServiceImpl implements SkuImagesCommandService {

    @Autowired
    private SkuImagesRepository skuImagesRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(SkuImagesCommand skuImagesCommand) {
        SkuImages skuImages = SkuImagesAssembler.toSkuImages(skuImagesCommand);
        SkuImagesCreateSpecification skuImagesCreateSpecification = new SkuImagesCreateSpecification();
        skuImagesCreateSpecification.isSatisfiedBy(skuImages);
        return skuImagesRepository.store(skuImages).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> ids) {
        if (EmptyUtil.isEmpty(ids)) {
            return false;
        }

        List<SkuImagesId> skuImagesIdList = new ArrayList<>();
        for (Long id : ids) {
            skuImagesIdList.add(new SkuImagesId(id));
        }
        return skuImagesRepository.remove(skuImagesIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return skuImagesRepository.remove(new SkuImagesId(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<SkuImagesCommand> skuImagesCommandList) {
        List<SkuImages> skuImagesList = ListUtil.listMapCollectToList(skuImagesCommandList, command ->
                SkuImagesAssembler.toSkuImages(command)
        );

        //校验
        SkuImagesCreateSpecification skuImagesCreateSpecification = new SkuImagesCreateSpecification();
        skuImagesList.forEach(skuImages->{
            skuImagesCreateSpecification.isSatisfiedBy(skuImages);
        });

        return skuImagesRepository.store(skuImagesList).stream().map(id->id.getId()).toArray(Long[]::new);
    }
}
