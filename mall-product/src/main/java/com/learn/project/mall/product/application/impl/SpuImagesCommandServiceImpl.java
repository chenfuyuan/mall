package com.learn.project.mall.product.application.impl;

import com.learn.project.mall.product.application.SpuImagesCommandService;
import com.learn.project.mall.product.application.assembler.SpuImagesAssembler;
import com.learn.project.mall.product.application.command.SpuImagesCommand;
import com.learn.project.mall.product.domain.model.spuImages.SpuImages;
import com.learn.project.mall.product.domain.model.spuImages.SpuImagesId;
import com.learn.project.mall.product.domain.model.spuImages.SpuImagesRepository;
import com.learn.project.mall.product.domain.specification.SpuImagesCreateSpecification;
import com.uptool.core.util.EmptyUtil;
import com.uptool.core.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * spu图片-命令服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("spuImagesCommandService")
public class SpuImagesCommandServiceImpl implements SpuImagesCommandService {

    @Autowired
    private SpuImagesRepository spuImagesRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdate(SpuImagesCommand spuImagesCommand) {
        SpuImages spuImages = SpuImagesAssembler.toSpuImages(spuImagesCommand);
        SpuImagesCreateSpecification spuImagesCreateSpecification = new SpuImagesCreateSpecification();
        spuImagesCreateSpecification.isSatisfiedBy(spuImages);
        return spuImagesRepository.store(spuImages).getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Collection<Long> ids) {
        if (EmptyUtil.isEmpty(ids)) {
            return false;
        }

        List<SpuImagesId> spuImagesIdList = new ArrayList<>();
        for (Long id : ids) {
            spuImagesIdList.add(new SpuImagesId(id));
        }
        return spuImagesRepository.remove(spuImagesIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        return spuImagesRepository.remove(new SpuImagesId(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long[] batchSaveOrUpdate(List<SpuImagesCommand> spuImagesCommandList) {
        List<SpuImages> spuImagesList = ListUtil.listMapCollectToList(spuImagesCommandList, command ->
                SpuImagesAssembler.toSpuImages(command)
        );

        //校验
        SpuImagesCreateSpecification spuImagesCreateSpecification = new SpuImagesCreateSpecification();
        spuImagesList.forEach(spuImages->{
            spuImagesCreateSpecification.isSatisfiedBy(spuImages);
        });

        return spuImagesRepository.store(spuImagesList).stream().map(id->id.getId()).toArray(Long[]::new);
    }
}
