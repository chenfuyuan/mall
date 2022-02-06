package com.learn.project.mall.product.application.assembler;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.EntityId;
import com.learn.project.mall.product.application.command.AttrCommand;
import com.learn.project.mall.product.application.dto.AttrDto;
import com.learn.project.mall.product.domain.model.attr.Attr;
import com.learn.project.mall.product.domain.model.attr.AttrId;
import com.uptool.core.util.EmptyUtil;

/**
 * 商品属性-装配器
 * 用于将 应用层对象与业务层对象进行转换
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public class AttrAssembler {

    /**
     * 转换成领域对象
     * @param command 命令对象
     * @return
     */
    public static Attr toAttr(AttrCommand command) {
        if (EmptyUtil.isEmpty(command)) {
            return null;
        }
        Attr result = new Attr();
        result.setAttrId(command.getAttrId() == null ? null: new AttrId(command.getAttrId()));

        result.setAttrName(command.getAttrName());
        result.setSearchType(command.getSearchType());
        result.setIcon(command.getIcon());
        result.setValueSelect(command.getValueSelect());
        result.setAttrType(command.getAttrType());
        result.setEnable(command.getEnable());
        result.setCatelogId(command.getCatelogId());
        result.setShowDesc(command.getShowDesc());

        result.setCommonInfo(CommonInfo.getInstance(command.getGmtCreate(), command.getGmtModified(), command.getIsDelete(), command.getUpdateVersion()));

        return result;
    }

    /**
     * 转换成数据传输对象
     * @param attr 领域对象
     * @return
     */
    public static AttrDto fromAttr(Attr attr) {
        if (EmptyUtil.isEmpty(attr)) {
            return null;
        }
        AttrDto result = new AttrDto();
        result.setAttrId(EntityId.getId(attr.getAttrId()));

        result.setAttrName(attr.getAttrName());
        result.setSearchType(attr.getSearchType());
        result.setIcon(attr.getIcon());
        result.setValueSelect(attr.getValueSelect());
        result.setAttrType(attr.getAttrType());
        result.setEnable(attr.getEnable());
        result.setCatelogId(attr.getCatelogId());
        result.setShowDesc(attr.getShowDesc());

        if (attr.getCommonInfo() != null) {
            result.setIsDelete(attr.getCommonInfo().getIsDeleteEnum().getValue());
            result.setGmtCreate(attr.getCommonInfo().getCreateTime());
            result.setGmtModified(attr.getCommonInfo().getUpdateTime());
            result.setUpdateVersion(attr.getCommonInfo().getVersion());
        }
        return result;
    }
}
