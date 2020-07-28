package com.learn.common.utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName TreeUtils
 * @Description 树形工具类
 * @Author chenfuyuan
 * @Date 2020-7-29 1:36
 * @Version 1.0
 */
public class TreeUtils<T extends TreeAble> {


    /**
     * list 转化成 树形结构 根据pid进行转换
     * @param data 数据源
     * @return 树形结构list
     */
    public List<T> listToTree(List<T> data) {
        //过滤
        return data.stream().filter(t -> {
            //过滤出一级节点
            return t.getPId() == 0;
        }).map(menu -> {
            //设置节点
            menu.setChildrens((List<T>) getChildrens(menu, data));
            return menu;
        }).sorted((menu1, menu2) -> {
            //排序
            return (menu1.getOrder() == null ? 0 : menu1.getOrder()) - (menu2.getOrder() == null ? 0 : menu2.getOrder());
        }).collect(Collectors.toList());

    }

    /**
     * 获取根节点的子节点集
     * @param root 根节点
     * @param data 数据源
     * @return
     */
    private List<T> getChildrens(T root, List<T> data) {
        return data.stream().filter(menu -> {
            //筛选出该节点的子节点
            return menu.getPId().equals(root.getId());
        }).map(menu -> {
            //递归寻找子节点
            menu.setChildrens(getChildrens(menu, data));
            return menu;
        }).sorted((menu1, menu2) -> {
            //排序
            return (menu1.getOrder() == null ? 0 : menu1.getOrder()) - (menu2.getOrder() == null ? 0 : menu2.getOrder());
        }).collect(Collectors.toList());
    }
}
