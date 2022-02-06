package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.AttrAttrgroupRelationCommand;
import com.learn.project.mall.product.application.dto.AttrAttrgroupRelationDto;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelation;
import com.learn.project.mall.product.domain.model.attrAttrgroupRelation.AttrAttrgroupRelationId;
import com.uptool.core.util.EmptyUtil;

/**
 * 属性&属性分组关联-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class AttrAttrgroupRelationAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static AttrAttrgroupRelation toAttrAttrgroupRelation(AttrAttrgroupRelationCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        AttrAttrgroupRelation result = new AttrAttrgroupRelation();
        result.setId(command.getId() == null ? null: new AttrAttrgroupRelationId(command.getId()));

        result.setAttrId(command.getAttrId());
        result.setAttrGroupId(command.getAttrGroupId());
        result.setAttrSort(command.getAttrSort());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param attrAttrgroupRelation 领域对象
     * @return
     */
    public static AttrAttrgroupRelationDto fromAttrAttrgroupRelation(AttrAttrgroupRelation attrAttrgroupRelation) {
        if (EmptyUtil.isEmpty(attrAttrgroupRelation)) {
            return null;
        }
        AttrAttrgroupRelationDto result = new AttrAttrgroupRelationDto();
        result.setId(EntityId.getId(attrAttrgroupRelation.getId()));

        result.setAttrId(attrAttrgroupRelation.getAttrId());
        result.setAttrGroupId(attrAttrgroupRelation.getAttrGroupId());
        result.setAttrSort(attrAttrgroupRelation.getAttrSort());

        if (attrAttrgroupRelation.getCommonInfo() != null) {
            result.setIsDelete(attrAttrgroupRelation.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(attrAttrgroupRelation.getCommonInfo().getCreateTime());
            result.setGmtModified(attrAttrgroupRelation.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(attrAttrgroupRelation.getCommonInfo().getVersion());
        }
        return result;
    }
}
