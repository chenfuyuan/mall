package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValue;
import com.learn.project.mall.product.domain.model.productAttrValue.ProductAttrValueId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.ProductAttrValueDo;
import com.uptool.core.util.EmptyUtil;

/**
 * spu属性值-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class ProductAttrValueConverter {

    /**
     * 将领域对象转化为数据对象
     * @param productAttrValue 领域对象
     * @return
     */
    public static ProductAttrValueDo fromProductAttrValue(ProductAttrValue productAttrValue) {
        if (EmptyUtil.isNull(productAttrValue)) {
            return null;
        }
        ProductAttrValueDo result = new ProductAttrValueDo();
        ConverterUtil.fromDomainModelCommonInfo(result,productAttrValue,productAttrValue);

        result.setSpuId(productAttrValue.getSpuId());
        result.setAttrId(productAttrValue.getAttrId());
        result.setAttrName(productAttrValue.getAttrName());
        result.setAttrValue(productAttrValue.getAttrValue());
        result.setAttrSort(productAttrValue.getAttrSort());
        result.setQuickShow(productAttrValue.getQuickShow());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param productAttrValueDo 数据对象
     * @return
     */
    public static ProductAttrValue toProductAttrValue(ProductAttrValueDo productAttrValueDo) {
        if (EmptyUtil.isNull(productAttrValueDo)) {
            return null;
        }

        ProductAttrValue result = new ProductAttrValue();
        ConverterUtil.toDomainModelCommonInfo(result, productAttrValueDo);

        result.setId(new ProductAttrValueId(productAttrValueDo.getId()));

        result.setSpuId(productAttrValueDo.getSpuId());
        result.setAttrId(productAttrValueDo.getAttrId());
        result.setAttrName(productAttrValueDo.getAttrName());
        result.setAttrValue(productAttrValueDo.getAttrValue());
        result.setAttrSort(productAttrValueDo.getAttrSort());
        result.setQuickShow(productAttrValueDo.getQuickShow());
        return result;
    }
}
