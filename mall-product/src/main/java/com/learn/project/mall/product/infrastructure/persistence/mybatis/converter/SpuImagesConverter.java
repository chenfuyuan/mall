package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.spuImages.SpuImages;
import com.learn.project.mall.product.domain.model.spuImages.SpuImagesId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SpuImagesDo;
import com.uptool.core.util.EmptyUtil;

/**
 * spu图片-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class SpuImagesConverter {

    /**
     * 将领域对象转化为数据对象
     * @param spuImages 领域对象
     * @return
     */
    public static SpuImagesDo fromSpuImages(SpuImages spuImages) {
        if (EmptyUtil.isNull(spuImages)) {
            return null;
        }
        SpuImagesDo result = new SpuImagesDo();
        ConverterUtil.fromDomainModelCommonInfo(result,spuImages,spuImages);

        result.setSpuId(spuImages.getSpuId());
        result.setImgName(spuImages.getImgName());
        result.setImgUrl(spuImages.getImgUrl());
        result.setImgSort(spuImages.getImgSort());
        result.setDefaultImg(spuImages.getDefaultImg());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param spuImagesDo 数据对象
     * @return
     */
    public static SpuImages toSpuImages(SpuImagesDo spuImagesDo) {
        if (EmptyUtil.isNull(spuImagesDo)) {
            return null;
        }

        SpuImages result = new SpuImages();
        ConverterUtil.toDomainModelCommonInfo(result, spuImagesDo);

        result.setId(new SpuImagesId(spuImagesDo.getId()));

        result.setSpuId(spuImagesDo.getSpuId());
        result.setImgName(spuImagesDo.getImgName());
        result.setImgUrl(spuImagesDo.getImgUrl());
        result.setImgSort(spuImagesDo.getImgSort());
        result.setDefaultImg(spuImagesDo.getDefaultImg());
        return result;
    }
}
