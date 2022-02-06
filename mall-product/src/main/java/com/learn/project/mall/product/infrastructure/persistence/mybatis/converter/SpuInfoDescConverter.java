package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDesc;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDescId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SpuInfoDescDo;
import com.uptool.core.util.EmptyUtil;

/**
 * spu信息介绍-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class SpuInfoDescConverter {

    /**
     * 将领域对象转化为数据对象
     * @param spuInfoDesc 领域对象
     * @return
     */
    public static SpuInfoDescDo fromSpuInfoDesc(SpuInfoDesc spuInfoDesc) {
        if (EmptyUtil.isNull(spuInfoDesc)) {
            return null;
        }
        SpuInfoDescDo result = new SpuInfoDescDo();
        ConverterUtil.fromDomainModelCommonInfo(result,spuInfoDesc,spuInfoDesc);

        result.setDecript(spuInfoDesc.getDecript());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param spuInfoDescDo 数据对象
     * @return
     */
    public static SpuInfoDesc toSpuInfoDesc(SpuInfoDescDo spuInfoDescDo) {
        if (EmptyUtil.isNull(spuInfoDescDo)) {
            return null;
        }

        SpuInfoDesc result = new SpuInfoDesc();
        ConverterUtil.toDomainModelCommonInfo(result, spuInfoDescDo);

        result.setSpuId(new SpuInfoDescId(spuInfoDescDo.getSpuId()));

        result.setDecript(spuInfoDescDo.getDecript());
        return result;
    }
}
