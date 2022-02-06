package com.learn.project.mall.product.application;

import com.learn.project.mall.product.application.command.AttrGroupCommand;

import java.util.Collection;
import java.util.List;

/**
 * 属性分组-命令服务接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface AttrGroupCommandService {

    /**
     * 保存或更新
     * @param attrGroupCommand
     */
    Long saveOrUpdate(AttrGroupCommand attrGroupCommand);

    /**
     * 删除，根据传递过来的attrGroupId列表
     * @param attrGroupIds attrGroupId列表
     * @return
     */
    boolean delete(Collection<Long> attrGroupIds);

    /**
     * 删除，根据传递过来的attrGroupId
     * @param attrGroupId attrGroupId
     * @return
     */
    boolean delete(Long attrGroupId);

    /**
     * 批量进行保存或更新
     * @param attrGroupCommandList
     * @return
     */
    Long[] batchSaveOrUpdate(List<AttrGroupCommand> attrGroupCommandList);
}
