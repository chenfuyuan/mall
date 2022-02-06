package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.CommonCommand;
import com.learn.project.mall.product.application.dto.CommonDto;
import com.learn.project.mall.product.domain.model.common.Common;
import com.learn.project.mall.product.domain.model.common.CommonId;
import com.uptool.core.util.EmptyUtil;

/**
 * 通用测试-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class CommonAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static Common toCommon(CommonCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        Common result = new Common();
        result.setCommonId(command.getCommonId() == null ? null: new CommonId(command.getCommonId()));

        result.setName(command.getName());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param common 领域对象
     * @return
     */
    public static CommonDto fromCommon(Common common) {
        if (EmptyUtil.isEmpty(common)) {
            return null;
        }
        CommonDto result = new CommonDto();
        result.setCommonId(EntityId.getId(common.getCommonId()));

        result.setName(common.getName());

        if (common.getCommonInfo() != null) {
            result.setIsDelete(common.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(common.getCommonInfo().getCreateTime());
            result.setGmtModified(common.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(common.getCommonInfo().getVersion());
        }
        return result;
    }
}
