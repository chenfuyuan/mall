package com.learn.project.mall.product.application;

import com.learn.project.mall.product.application.command.CommonCommand;

import java.util.Collection;
import java.util.List;

/**
 * 通用测试-命令服务接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public interface CommonCommandService {

    /**
     * 保存或更新
     * @param commonCommand
     */
    Long saveOrUpdate(CommonCommand commonCommand);

    /**
     * 删除，根据传递过来的commonId列表
     * @param commonIds commonId列表
     * @return
     */
    boolean delete(Collection<Long> commonIds);

    /**
     * 删除，根据传递过来的commonId
     * @param commonId commonId
     * @return
     */
    boolean delete(Long commonId);

    /**
     * 批量进行保存或更新
     * @param commonCommandList
     * @return
     */
    Long[] batchSaveOrUpdate(List<CommonCommand> commonCommandList);
}
