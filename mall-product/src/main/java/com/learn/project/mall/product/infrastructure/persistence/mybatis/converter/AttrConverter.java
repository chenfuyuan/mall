package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.attr.Attr;
import com.learn.project.mall.product.domain.model.attr.AttrId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.AttrDo;
import com.uptool.core.util.EmptyUtil;

/**
 * 商品属性-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class AttrConverter {

    /**
     * 将领域对象转化为数据对象
     * @param attr 领域对象
     * @return
     */
    public static AttrDo fromAttr(Attr attr) {
        if (EmptyUtil.isNull(attr)) {
            return null;
        }
        AttrDo result = new AttrDo();
        ConverterUtil.fromDomainModelCommonInfo(result,attr,attr);

        result.setAttrName(attr.getAttrName());
        result.setSearchType(attr.getSearchType());
        result.setIcon(attr.getIcon());
        result.setValueSelect(attr.getValueSelect());
        result.setAttrType(attr.getAttrType());
        result.setEnable(attr.getEnable());
        result.setCatelogId(attr.getCatelogId());
        result.setShowDesc(attr.getShowDesc());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param attrDo 数据对象
     * @return
     */
    public static Attr toAttr(AttrDo attrDo) {
        if (EmptyUtil.isNull(attrDo)) {
            return null;
        }

        Attr result = new Attr();
        ConverterUtil.toDomainModelCommonInfo(result, attrDo);

        result.setAttrId(new AttrId(attrDo.getAttrId()));

        result.setAttrName(attrDo.getAttrName());
        result.setSearchType(attrDo.getSearchType());
        result.setIcon(attrDo.getIcon());
        result.setValueSelect(attrDo.getValueSelect());
        result.setAttrType(attrDo.getAttrType());
        result.setEnable(attrDo.getEnable());
        result.setCatelogId(attrDo.getCatelogId());
        result.setShowDesc(attrDo.getShowDesc());
        return result;
    }
}
