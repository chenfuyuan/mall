package com.learn.project.mall.product.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.learn.project.common.mybatis.util.PageUtils;
import com.learn.project.common.mybatis.util.Query;
import com.learn.project.mall.product.application.SkuImagesQueryService;
import com.learn.project.mall.product.application.assembler.SkuImagesAssembler;
import com.learn.project.mall.product.application.dto.SkuImagesDto;
import com.learn.project.mall.product.domain.model.skuImages.SkuImages;
import com.learn.project.mall.product.domain.model.skuImages.SkuImagesId;
import com.learn.project.mall.product.domain.model.skuImages.SkuImagesRepository;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SkuImagesDo;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.mapper.SkuImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * sku图片-查询服务实现类
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Service("skuImagesQueryService")
public class SkuImagesQueryServiceImpl implements SkuImagesQueryService {

    @Autowired
    private SkuImagesMapper skuImagesMapper;

    @Autowired
    private SkuImagesRepository skuImagesRepository;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //填充查询条件
        QueryWrapper<SkuImagesDo> queryWrapper = new QueryWrapper<>();

        //查询
        IPage<SkuImagesDo> page = skuImagesMapper.selectPage(new Query().getPage(params),queryWrapper);

        //封装分页
        return new PageUtils(page);
    }

    @Override
    public SkuImagesDto getById(Long id) {
        SkuImages skuImages = skuImagesRepository.find(new SkuImagesId(id));
        return SkuImagesAssembler.fromSkuImages(skuImages);
    }


}
