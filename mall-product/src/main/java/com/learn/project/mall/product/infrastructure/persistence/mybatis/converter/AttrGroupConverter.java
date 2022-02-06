package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroup;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroupId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.AttrGroupDo;
import com.uptool.core.util.EmptyUtil;

/**
 * 属性分组-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class AttrGroupConverter {

    /**
     * 将领域对象转化为数据对象
     * @param attrGroup 领域对象
     * @return
     */
    public static AttrGroupDo fromAttrGroup(AttrGroup attrGroup) {
        if (EmptyUtil.isNull(attrGroup)) {
            return null;
        }
        AttrGroupDo result = new AttrGroupDo();
        ConverterUtil.fromDomainModelCommonInfo(result,attrGroup,attrGroup);

        result.setAttrGroupName(attrGroup.getAttrGroupName());
        result.setSort(attrGroup.getSort());
        result.setDescript(attrGroup.getDescript());
        result.setIcon(attrGroup.getIcon());
        result.setCatelogId(attrGroup.getCatelogId());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param attrGroupDo 数据对象
     * @return
     */
    public static AttrGroup toAttrGroup(AttrGroupDo attrGroupDo) {
        if (EmptyUtil.isNull(attrGroupDo)) {
            return null;
        }

        AttrGroup result = new AttrGroup();
        ConverterUtil.toDomainModelCommonInfo(result, attrGroupDo);

        result.setAttrGroupId(new AttrGroupId(attrGroupDo.getAttrGroupId()));

        result.setAttrGroupName(attrGroupDo.getAttrGroupName());
        result.setSort(attrGroupDo.getSort());
        result.setDescript(attrGroupDo.getDescript());
        result.setIcon(attrGroupDo.getIcon());
        result.setCatelogId(attrGroupDo.getCatelogId());
        return result;
    }
}
