package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.AttrGroupCommand;
import com.learn.project.mall.product.application.dto.AttrGroupDto;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroup;
import com.learn.project.mall.product.domain.model.attrGroup.AttrGroupId;
import com.uptool.core.util.EmptyUtil;

/**
 * 属性分组-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public class AttrGroupAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static AttrGroup toAttrGroup(AttrGroupCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        AttrGroup result = new AttrGroup();
        result.setAttrGroupId(command.getAttrGroupId() == null ? null: new AttrGroupId(command.getAttrGroupId()));

        result.setAttrGroupName(command.getAttrGroupName());
        result.setSort(command.getSort());
        result.setDescript(command.getDescript());
        result.setIcon(command.getIcon());
        result.setCatelogId(command.getCatelogId());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param attrGroup 领域对象
     * @return
     */
    public static AttrGroupDto fromAttrGroup(AttrGroup attrGroup) {
        if (EmptyUtil.isEmpty(attrGroup)) {
            return null;
        }
        AttrGroupDto result = new AttrGroupDto();
        result.setAttrGroupId(EntityId.getId(attrGroup.getAttrGroupId()));

        result.setAttrGroupName(attrGroup.getAttrGroupName());
        result.setSort(attrGroup.getSort());
        result.setDescript(attrGroup.getDescript());
        result.setIcon(attrGroup.getIcon());
        result.setCatelogId(attrGroup.getCatelogId());

        if (attrGroup.getCommonInfo() != null) {
            result.setIsDelete(attrGroup.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(attrGroup.getCommonInfo().getCreateTime());
            result.setGmtModified(attrGroup.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(attrGroup.getCommonInfo().getVersion());
        }
        return result;
    }
}
