package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.BrandCommand;
import com.learn.project.mall.product.application.dto.BrandDto;
import com.learn.project.mall.product.domain.model.brand.Brand;
import com.learn.project.mall.product.domain.model.brand.BrandId;
import com.uptool.core.util.EmptyUtil;

/**
 * 品牌-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class BrandAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static Brand toBrand(BrandCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        Brand result = new Brand();
        result.setBrandId(command.getBrandId() == null ? null: new BrandId(command.getBrandId()));

        result.setName(command.getName());
        result.setLogo(command.getLogo());
        result.setDescript(command.getDescript());
        result.setShowStatus(command.getShowStatus());
        result.setFirstLetter(command.getFirstLetter());
        result.setSort(command.getSort());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param brand 领域对象
     * @return
     */
    public static BrandDto fromBrand(Brand brand) {
        if (EmptyUtil.isEmpty(brand)) {
            return null;
        }
        BrandDto result = new BrandDto();
        result.setBrandId(EntityId.getId(brand.getBrandId()));

        result.setName(brand.getName());
        result.setLogo(brand.getLogo());
        result.setDescript(brand.getDescript());
        result.setShowStatus(brand.getShowStatus());
        result.setFirstLetter(brand.getFirstLetter());
        result.setSort(brand.getSort());

        if (brand.getCommonInfo() != null) {
            result.setIsDelete(brand.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(brand.getCommonInfo().getCreateTime());
            result.setGmtModified(brand.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(brand.getCommonInfo().getVersion());
        }
        return result;
    }
}
