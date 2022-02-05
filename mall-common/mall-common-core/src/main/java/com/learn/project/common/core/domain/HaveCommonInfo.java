package com.learn.project.common.core.domain;

/**
 * 是否含有公共字段
 * createTime,updateTime,updateVersion,isDelete
 *
 * @author chenfuyuan
 * @date 2022/2/5 20:18
 */
public interface HaveCommonInfo {

    /**
     * 获取公共字段
     * @return
     */
    CommonInfo getCommonInfo();

    /**
     * 设置公共字段
     * @param commonInfo
     */
    void setCommonInfo(CommonInfo commonInfo);
}
