package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.SpuInfoCommand;
import com.learn.project.mall.product.application.dto.SpuInfoDto;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfo;
import com.learn.project.mall.product.domain.model.spuInfo.SpuInfoId;
import com.uptool.core.util.EmptyUtil;

/**
 * spu信息-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class SpuInfoAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static SpuInfo toSpuInfo(SpuInfoCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        SpuInfo result = new SpuInfo();
        result.setId(command.getId() == null ? null: new SpuInfoId(command.getId()));

        result.setSpuName(command.getSpuName());
        result.setSpuDescription(command.getSpuDescription());
        result.setCatalogId(command.getCatalogId());
        result.setBrandId(command.getBrandId());
        result.setWeight(command.getWeight());
        result.setPublishStatus(command.getPublishStatus());
        result.setCreateTime(command.getCreateTime());
        result.setUpdateTime(command.getUpdateTime());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param spuInfo 领域对象
     * @return
     */
    public static SpuInfoDto fromSpuInfo(SpuInfo spuInfo) {
        if (EmptyUtil.isEmpty(spuInfo)) {
            return null;
        }
        SpuInfoDto result = new SpuInfoDto();
        result.setId(EntityId.getId(spuInfo.getId()));

        result.setSpuName(spuInfo.getSpuName());
        result.setSpuDescription(spuInfo.getSpuDescription());
        result.setCatalogId(spuInfo.getCatalogId());
        result.setBrandId(spuInfo.getBrandId());
        result.setWeight(spuInfo.getWeight());
        result.setPublishStatus(spuInfo.getPublishStatus());
        result.setCreateTime(spuInfo.getCreateTime());
        result.setUpdateTime(spuInfo.getUpdateTime());

        if (spuInfo.getCommonInfo() != null) {
            result.setIsDelete(spuInfo.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(spuInfo.getCommonInfo().getCreateTime());
            result.setGmtModified(spuInfo.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(spuInfo.getCommonInfo().getVersion());
        }
        return result;
    }
}
