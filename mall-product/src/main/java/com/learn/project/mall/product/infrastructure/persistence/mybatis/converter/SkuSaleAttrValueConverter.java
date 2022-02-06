package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValue;
import com.learn.project.mall.product.domain.model.skuSaleAttrValue.SkuSaleAttrValueId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.SkuSaleAttrValueDo;
import com.uptool.core.util.EmptyUtil;

/**
 * sku销售属性&值-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class SkuSaleAttrValueConverter {

    /**
     * 将领域对象转化为数据对象
     * @param skuSaleAttrValue 领域对象
     * @return
     */
    public static SkuSaleAttrValueDo fromSkuSaleAttrValue(SkuSaleAttrValue skuSaleAttrValue) {
        if (EmptyUtil.isNull(skuSaleAttrValue)) {
            return null;
        }
        SkuSaleAttrValueDo result = new SkuSaleAttrValueDo();
        ConverterUtil.fromDomainModelCommonInfo(result,skuSaleAttrValue,skuSaleAttrValue);

        result.setSkuId(skuSaleAttrValue.getSkuId());
        result.setAttrId(skuSaleAttrValue.getAttrId());
        result.setAttrName(skuSaleAttrValue.getAttrName());
        result.setAttrValue(skuSaleAttrValue.getAttrValue());
        result.setAttrSort(skuSaleAttrValue.getAttrSort());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param skuSaleAttrValueDo 数据对象
     * @return
     */
    public static SkuSaleAttrValue toSkuSaleAttrValue(SkuSaleAttrValueDo skuSaleAttrValueDo) {
        if (EmptyUtil.isNull(skuSaleAttrValueDo)) {
            return null;
        }

        SkuSaleAttrValue result = new SkuSaleAttrValue();
        ConverterUtil.toDomainModelCommonInfo(result, skuSaleAttrValueDo);

        result.setId(new SkuSaleAttrValueId(skuSaleAttrValueDo.getId()));

        result.setSkuId(skuSaleAttrValueDo.getSkuId());
        result.setAttrId(skuSaleAttrValueDo.getAttrId());
        result.setAttrName(skuSaleAttrValueDo.getAttrName());
        result.setAttrValue(skuSaleAttrValueDo.getAttrValue());
        result.setAttrSort(skuSaleAttrValueDo.getAttrSort());
        return result;
    }
}
