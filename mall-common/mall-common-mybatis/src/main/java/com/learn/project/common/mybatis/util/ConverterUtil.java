package com.learn.project.common.mybatis.util;

import com.learn.project.common.core.domain.CommonInfo;
import com.learn.project.common.core.domain.Entity;
import com.learn.project.common.core.domain.HaveCommonInfo;
import com.learn.project.common.core.domain.IsDeleteEnum;
import com.uptool.core.util.EmptyUtil;

/**
 * 抽象的转换器类
 *
 * @author chenfuyuan
 * @date 2022/2/5 18:02
 */
public class ConverterUtil {


    /**
     * 数据库对象和领域对象的公共字段的互相转换
     * 公共字段包括 id,createTime,updateTime,updateVersion,isDelete
     * @param baseDo
     * @param doMainModel
     */
    public static void fromDomainModelCommonInfo(BaseDo baseDo, Entity doMainModel, HaveCommonInfo commonInfoModel) {
        //baseDo为空，或 doMainModel和commonInfoModel都为空
        if (EmptyUtil.isNull(baseDo) || (EmptyUtil.isNull(doMainModel) && EmptyUtil.isNull(commonInfoModel))) {
            return;
        }

        if (doMainModel != null && doMainModel.getUniqueLabel() != null) {
            baseDo.inUniqueLabel(doMainModel.getUniqueLabel().getId());
        }

        if (commonInfoModel != null && commonInfoModel.getCommonInfo() != null) {
            CommonInfo commonInfo = commonInfoModel.getCommonInfo();
            baseDo.setGmtCreate(commonInfo.getCreateTime());
            baseDo.setGmtModified(commonInfo.getUpdateTime());
            baseDo.setIsDelete(IsDeleteEnum.getValue(commonInfo.getIsDeleteEnum()));
        }
    }

    public static void toDomainModelCommonInfo(HaveCommonInfo doMainModel, BaseDo baseDo) {
        if (!EmptyUtil.isAllNoNull(doMainModel,baseDo)) {
            //= doMainModel!=null && baseDo!=null
            return;
        }

        doMainModel.setCommonInfo(getCommonInfoByDo(baseDo));
    }

    public static CommonInfo getCommonInfoByDo(BaseDo baseDo) {
        if (baseDo == null) {
            return null;
        }

        return new CommonInfo(baseDo.getGmtCreate(), baseDo.getGmtModified(), IsDeleteEnum.getEnum(baseDo.getIsDelete()), baseDo.getUpdateVersion());
    }
}
