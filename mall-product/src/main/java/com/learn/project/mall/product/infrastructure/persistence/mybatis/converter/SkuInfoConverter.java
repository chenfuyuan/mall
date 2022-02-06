package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfo;
import com.learn.project.mall.product.domain.model.skuInfo.SkuInfoId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SkuInfoDo;
import com.uptool.core.util.EmptyUtil;

/**
 * sku信息-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class SkuInfoConverter {

    /**
     * 将领域对象转化为数据对象
     * @param skuInfo 领域对象
     * @return
     */
    public static SkuInfoDo fromSkuInfo(SkuInfo skuInfo) {
        if (EmptyUtil.isNull(skuInfo)) {
            return null;
        }
        SkuInfoDo result = new SkuInfoDo();
        ConverterUtil.fromDomainModelCommonInfo(result,skuInfo,skuInfo);

        result.setSpuId(skuInfo.getSpuId());
        result.setSkuName(skuInfo.getSkuName());
        result.setSkuDesc(skuInfo.getSkuDesc());
        result.setCatalogId(skuInfo.getCatalogId());
        result.setBrandId(skuInfo.getBrandId());
        result.setSkuDefaultImg(skuInfo.getSkuDefaultImg());
        result.setSkuTitle(skuInfo.getSkuTitle());
        result.setSkuSubtitle(skuInfo.getSkuSubtitle());
        result.setPrice(skuInfo.getPrice());
        result.setSaleCount(skuInfo.getSaleCount());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param skuInfoDo 数据对象
     * @return
     */
    public static SkuInfo toSkuInfo(SkuInfoDo skuInfoDo) {
        if (EmptyUtil.isNull(skuInfoDo)) {
            return null;
        }

        SkuInfo result = new SkuInfo();
        ConverterUtil.toDomainModelCommonInfo(result, skuInfoDo);

        result.setSkuId(new SkuInfoId(skuInfoDo.getSkuId()));

        result.setSpuId(skuInfoDo.getSpuId());
        result.setSkuName(skuInfoDo.getSkuName());
        result.setSkuDesc(skuInfoDo.getSkuDesc());
        result.setCatalogId(skuInfoDo.getCatalogId());
        result.setBrandId(skuInfoDo.getBrandId());
        result.setSkuDefaultImg(skuInfoDo.getSkuDefaultImg());
        result.setSkuTitle(skuInfoDo.getSkuTitle());
        result.setSkuSubtitle(skuInfoDo.getSkuSubtitle());
        result.setPrice(skuInfoDo.getPrice());
        result.setSaleCount(skuInfoDo.getSaleCount());
        return result;
    }
}
