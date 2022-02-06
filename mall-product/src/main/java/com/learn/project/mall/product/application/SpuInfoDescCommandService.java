package com.learn.project.mall.product.application;

import com.learn.project.mall.product.application.command.SpuInfoDescCommand;

import java.util.Collection;
import java.util.List;

/**
 * spu信息介绍-命令服务接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface SpuInfoDescCommandService {

    /**
     * 保存或更新
     * @param spuInfoDescCommand
     */
    Long saveOrUpdate(SpuInfoDescCommand spuInfoDescCommand);

    /**
     * 删除，根据传递过来的spuId列表
     * @param spuIds spuId列表
     * @return
     */
    boolean delete(Collection<Long> spuIds);

    /**
     * 删除，根据传递过来的spuId
     * @param spuId spuId
     * @return
     */
    boolean delete(Long spuId);

    /**
     * 批量进行保存或更新
     * @param spuInfoDescCommandList
     * @return
     */
    Long[] batchSaveOrUpdate(List<SpuInfoDescCommand> spuInfoDescCommandList);
}
