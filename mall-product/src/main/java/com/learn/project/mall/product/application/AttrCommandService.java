package com.learn.project.mall.product.application;

import com.learn.project.mall.product.application.command.AttrCommand;

import java.util.Collection;
import java.util.List;

/**
 * 商品属性-命令服务接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface AttrCommandService {

    /**
     * 保存或更新
     * @param attrCommand
     */
    Long saveOrUpdate(AttrCommand attrCommand);

    /**
     * 删除，根据传递过来的attrId列表
     * @param attrIds attrId列表
     * @return
     */
    boolean delete(Collection<Long> attrIds);

    /**
     * 删除，根据传递过来的attrId
     * @param attrId attrId
     * @return
     */
    boolean delete(Long attrId);

    /**
     * 批量进行保存或更新
     * @param attrCommandList
     * @return
     */
    Long[] batchSaveOrUpdate(List<AttrCommand> attrCommandList);
}
