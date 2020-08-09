package com.learn.common.utils;

import java.util.List;

/**
 * @InterfaceName TreeAble
 * @Description 标记可以被树形结构展示的数据
 * @Author chenfuyuan
 * @Date 2020-7-29 1:38
 * @Version 1.0
 */
public interface TreeAble <T>{
    /**
     * @return 获取父节点Id
     */
    Long getPId();

    /**
     * 设置子节点
     * @param childrens 子节点集合
     */
    void setChildrens(List<T> childrens);

    /**
     * @return 获取顺序
     */
    Integer getOrder();

    /**
     * 获取id
     * @return id
     */
    Long getId();
}
