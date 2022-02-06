package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.SpuInfoDescCommand;
import com.learn.project.mall.product.application.dto.SpuInfoDescDto;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDesc;
import com.learn.project.mall.product.domain.model.spuInfoDesc.SpuInfoDescId;
import com.uptool.core.util.EmptyUtil;

/**
 * spu信息介绍-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class SpuInfoDescAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static SpuInfoDesc toSpuInfoDesc(SpuInfoDescCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        SpuInfoDesc result = new SpuInfoDesc();
        result.setSpuId(command.getSpuId() == null ? null: new SpuInfoDescId(command.getSpuId()));

        result.setDecript(command.getDecript());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param spuInfoDesc 领域对象
     * @return
     */
    public static SpuInfoDescDto fromSpuInfoDesc(SpuInfoDesc spuInfoDesc) {
        if (EmptyUtil.isEmpty(spuInfoDesc)) {
            return null;
        }
        SpuInfoDescDto result = new SpuInfoDescDto();
        result.setSpuId(EntityId.getId(spuInfoDesc.getSpuId()));

        result.setDecript(spuInfoDesc.getDecript());

        if (spuInfoDesc.getCommonInfo() != null) {
            result.setIsDelete(spuInfoDesc.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(spuInfoDesc.getCommonInfo().getCreateTime());
            result.setGmtModified(spuInfoDesc.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(spuInfoDesc.getCommonInfo().getVersion());
        }
        return result;
    }
}
