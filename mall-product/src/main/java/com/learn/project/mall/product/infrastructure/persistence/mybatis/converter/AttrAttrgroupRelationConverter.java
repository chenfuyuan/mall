package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelation;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelationId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.AttrAttrgroupRelationDo;
import com.uptool.core.util.EmptyUtil;

/**
 * 属性&属性分组关联-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class AttrAttrgroupRelationConverter {

    /**
     * 将领域对象转化为数据对象
     * @param attrAttrgroupRelation 领域对象
     * @return
     */
    public static AttrAttrgroupRelationDo fromAttrAttrgroupRelation(AttrAttrgroupRelation attrAttrgroupRelation) {
        if (EmptyUtil.isNull(attrAttrgroupRelation)) {
            return null;
        }
        AttrAttrgroupRelationDo result = new AttrAttrgroupRelationDo();
        ConverterUtil.fromDomainModelCommonInfo(result,attrAttrgroupRelation,attrAttrgroupRelation);

        result.setAttrId(attrAttrgroupRelation.getAttrId());
        result.setAttrGroupId(attrAttrgroupRelation.getAttrGroupId());
        result.setAttrSort(attrAttrgroupRelation.getAttrSort());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param attrAttrgroupRelationDo 数据对象
     * @return
     */
    public static AttrAttrgroupRelation toAttrAttrgroupRelation(AttrAttrgroupRelationDo attrAttrgroupRelationDo) {
        if (EmptyUtil.isNull(attrAttrgroupRelationDo)) {
            return null;
        }

        AttrAttrgroupRelation result = new AttrAttrgroupRelation();
        ConverterUtil.toDomainModelCommonInfo(result, attrAttrgroupRelationDo);

        result.setId(new AttrAttrgroupRelationId(attrAttrgroupRelationDo.getId()));

        result.setAttrId(attrAttrgroupRelationDo.getAttrId());
        result.setAttrGroupId(attrAttrgroupRelationDo.getAttrGroupId());
        result.setAttrSort(attrAttrgroupRelationDo.getAttrSort());
        return result;
    }
}
