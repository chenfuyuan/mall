package com.learn.project.mall.product.application;

import com.learn.project.mall.product.application.command.SpuInfoCommand;

import java.util.Collection;
import java.util.List;

/**
 * spu信息-命令服务接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public interface SpuInfoCommandService {

    /**
     * 保存或更新
     * @param spuInfoCommand
     */
    Long saveOrUpdate(SpuInfoCommand spuInfoCommand);

    /**
     * 删除，根据传递过来的id列表
     * @param ids id列表
     * @return
     */
    boolean delete(Collection<Long> ids);

    /**
     * 删除，根据传递过来的id
     * @param id id
     * @return
     */
    boolean delete(Long id);

    /**
     * 批量进行保存或更新
     * @param spuInfoCommandList
     * @return
     */
    Long[] batchSaveOrUpdate(List<SpuInfoCommand> spuInfoCommandList);
}
