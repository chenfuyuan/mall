package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.GeneralCommand;
import com.learn.project.mall.product.application.dto.GeneralDto;
import com.learn.project.mall.product.domain.model.general.General;
import com.learn.project.mall.product.domain.model.general.GeneralId;
import com.uptool.core.util.EmptyUtil;

/**
 * 通用测试-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class GeneralAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static General toGeneral(GeneralCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        General result = new General();
        result.setId(command.getId() == null ? null: new GeneralId(command.getId()));

        result.setName(command.getName());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param general 领域对象
     * @return
     */
    public static GeneralDto fromGeneral(General general) {
        if (EmptyUtil.isEmpty(general)) {
            return null;
        }
        GeneralDto result = new GeneralDto();
        result.setId(EntityId.getId(general.getId()));

        result.setName(general.getName());

        if (general.getCommonInfo() != null) {
            result.setIsDelete(general.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(general.getCommonInfo().getCreateTime());
            result.setGmtModified(general.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(general.getCommonInfo().getVersion());
        }
        return result;
    }
}
