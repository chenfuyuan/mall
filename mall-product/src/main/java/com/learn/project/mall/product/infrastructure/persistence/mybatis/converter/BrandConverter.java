package com.learn.project.mall.product.infrastructure.persistence.mybatis.converter;

import com.learn.project.common.mybatis.util.ConverterUtil;
import com.learn.project.mall.product.domain.model.brand.Brand;
import com.learn.project.mall.product.domain.model.brand.BrandId;
import com.learn.project.mall.product.infrastructure.persistence.mybatis.entity.BrandDo;
import com.uptool.core.util.EmptyUtil;

/**
 * 品牌-转换器对象
 * 用于Do与领域对象的相互转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class BrandConverter {

    /**
     * 将领域对象转化为数据对象
     * @param brand 领域对象
     * @return
     */
    public static BrandDo fromBrand(Brand brand) {
        if (EmptyUtil.isNull(brand)) {
            return null;
        }
        BrandDo result = new BrandDo();
        ConverterUtil.fromDomainModelCommonInfo(result,brand,brand);

        result.setName(brand.getName());
        result.setLogo(brand.getLogo());
        result.setDescript(brand.getDescript());
        result.setShowStatus(brand.getShowStatus());
        result.setFirstLetter(brand.getFirstLetter());
        result.setSort(brand.getSort());

        return result;
    }

    /**
     * 将数据对象转化为领域对象
     * @param brandDo 数据对象
     * @return
     */
    public static Brand toBrand(BrandDo brandDo) {
        if (EmptyUtil.isNull(brandDo)) {
            return null;
        }

        Brand result = new Brand();
        ConverterUtil.toDomainModelCommonInfo(result, brandDo);

        result.setBrandId(new BrandId(brandDo.getBrandId()));

        result.setName(brandDo.getName());
        result.setLogo(brandDo.getLogo());
        result.setDescript(brandDo.getDescript());
        result.setShowStatus(brandDo.getShowStatus());
        result.setFirstLetter(brandDo.getFirstLetter());
        result.setSort(brandDo.getSort());
        return result;
    }
}
