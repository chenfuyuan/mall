package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.skuImages.SkuImages;
import com.learn.project.mall.product.domain.model.skuImages.SkuImagesId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SkuImagesDo;
import com.uptool.core.util.EmptyUtil;

/**
 * sku图片-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class SkuImagesConverter {

    /**
     * 将领域对象转化为数据对象
     * @param skuImages 领域对象
     * @return
     */
    public static SkuImagesDo fromSkuImages(SkuImages skuImages) {
        if (EmptyUtil.isNull(skuImages)) {
            return null;
        }
        SkuImagesDo result = new SkuImagesDo();
        ConverterUtil.fromDomainModelCommonInfo(result,skuImages,skuImages);

        result.setSkuId(skuImages.getSkuId());
        result.setImgUrl(skuImages.getImgUrl());
        result.setImgSort(skuImages.getImgSort());
        result.setDefaultImg(skuImages.getDefaultImg());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param skuImagesDo 数据对象
     * @return
     */
    public static SkuImages toSkuImages(SkuImagesDo skuImagesDo) {
        if (EmptyUtil.isNull(skuImagesDo)) {
            return null;
        }

        SkuImages result = new SkuImages();
        ConverterUtil.toDomainModelCommonInfo(result, skuImagesDo);

        result.setId(new SkuImagesId(skuImagesDo.getId()));

        result.setSkuId(skuImagesDo.getSkuId());
        result.setImgUrl(skuImagesDo.getImgUrl());
        result.setImgSort(skuImagesDo.getImgSort());
        result.setDefaultImg(skuImagesDo.getDefaultImg());
        return result;
    }
}
