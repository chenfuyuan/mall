package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.SpuImagesQueryService;
import com.learn.project.mall.product.application.assembler.SpuImagesAssembler;
import com.learn.project.mall.product.application.dto.SpuImagesDto;
import com.learn.project.mall.product.domain.model.spuImages.SpuImages;
import com.learn.project.mall.product.domain.model.spuImages.SpuImagesId;
import com.learn.project.mall.product.domain.model.spuImages.SpuImagesRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SpuImagesDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.SpuImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * spu图片-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("spuImagesQueryService")
public class SpuImagesQueryServiceImpl implements SpuImagesQueryService {

    @Autowired
    private SpuImagesMapper spuImagesMapper;

    @Autowired
    private SpuImagesRepository spuImagesRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<SpuImagesDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<SpuImagesDo> page = spuImagesMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public SpuImagesDto getById(Long id) {
        SpuImages spuImages = spuImagesRepository.find(new SpuImagesId(id));
        return SpuImagesAssembler.fromSpuImages(spuImages);
    }


}
