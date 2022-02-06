package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfo;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfoId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SpuInfoDo;
import com.uptool.core.util.EmptyUtil;

/**
 * spu信息-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class SpuInfoConverter {

    /**
     * 将领域对象转化为数据对象
     * @param spuInfo 领域对象
     * @return
     */
    public static SpuInfoDo fromSpuInfo(SpuInfo spuInfo) {
        if (EmptyUtil.isNull(spuInfo)) {
            return null;
        }
        SpuInfoDo result = new SpuInfoDo();
        ConverterUtil.fromDomainModelCommonInfo(result,spuInfo,spuInfo);

        result.setSpuName(spuInfo.getSpuName());
        result.setSpuDescription(spuInfo.getSpuDescription());
        result.setCatalogId(spuInfo.getCatalogId());
        result.setBrandId(spuInfo.getBrandId());
        result.setWeight(spuInfo.getWeight());
        result.setPublishStatus(spuInfo.getPublishStatus());
        result.setCreateTime(spuInfo.getCreateTime());
        result.setUpdateTime(spuInfo.getUpdateTime());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param spuInfoDo 数据对象
     * @return
     */
    public static SpuInfo toSpuInfo(SpuInfoDo spuInfoDo) {
        if (EmptyUtil.isNull(spuInfoDo)) {
            return null;
        }

        SpuInfo result = new SpuInfo();
        ConverterUtil.toDomainModelCommonInfo(result, spuInfoDo);

        result.setId(new SpuInfoId(spuInfoDo.getId()));

        result.setSpuName(spuInfoDo.getSpuName());
        result.setSpuDescription(spuInfoDo.getSpuDescription());
        result.setCatalogId(spuInfoDo.getCatalogId());
        result.setBrandId(spuInfoDo.getBrandId());
        result.setWeight(spuInfoDo.getWeight());
        result.setPublishStatus(spuInfoDo.getPublishStatus());
        result.setCreateTime(spuInfoDo.getCreateTime());
        result.setUpdateTime(spuInfoDo.getUpdateTime());
        return result;
    }
}
