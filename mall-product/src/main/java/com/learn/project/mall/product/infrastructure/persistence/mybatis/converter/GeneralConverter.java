package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.general.General;
import com.learn.project.mall.product.domain.model.general.GeneralId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.GeneralDo;
import com.uptool.core.util.EmptyUtil;

/**
 * 通用测试-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class GeneralConverter {

    /**
     * 将领域对象转化为数据对象
     * @param general 领域对象
     * @return
     */
    public static GeneralDo fromGeneral(General general) {
        if (EmptyUtil.isNull(general)) {
            return null;
        }
        GeneralDo result = new GeneralDo();
        ConverterUtil.fromDomainModelCommonInfo(result,general,general);

        result.setName(general.getName());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param generalDo 数据对象
     * @return
     */
    public static General toGeneral(GeneralDo generalDo) {
        if (EmptyUtil.isNull(generalDo)) {
            return null;
        }

        General result = new General();
        ConverterUtil.toDomainModelCommonInfo(result, generalDo);

        result.setId(new GeneralId(generalDo.getId()));

        result.setName(generalDo.getName());
        return result;
    }
}
