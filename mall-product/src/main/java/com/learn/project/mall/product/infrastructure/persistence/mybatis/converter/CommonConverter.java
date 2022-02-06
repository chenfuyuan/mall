package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.common.Common;
import com.learn.project.mall.product.domain.model.common.CommonId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.CommonDo;
import com.uptool.core.util.EmptyUtil;

/**
 * 通用测试-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class CommonConverter {

    /**
     * 将领域对象转化为数据对象
     * @param common 领域对象
     * @return
     */
    public static CommonDo fromCommon(Common common) {
        if (EmptyUtil.isNull(common)) {
            return null;
        }
        CommonDo result = new CommonDo();
        ConverterUtil.fromDomainModelCommonInfo(result,common,common);

        result.setName(common.getName());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param commonDo 数据对象
     * @return
     */
    public static Common toCommon(CommonDo commonDo) {
        if (EmptyUtil.isNull(commonDo)) {
            return null;
        }

        Common result = new Common();
        ConverterUtil.toDomainModelCommonInfo(result, commonDo);

        result.setCommonId(new CommonId(commonDo.getCommonId()));

        result.setName(commonDo.getName());
        return result;
    }
}
