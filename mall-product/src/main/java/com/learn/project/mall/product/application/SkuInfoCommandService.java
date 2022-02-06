package com.learn.project.mall.product.application;

import com.learn.project.mall.product.application.command.SkuInfoCommand;

import java.util.Collection;
import java.util.List;

/**
 * sku信息-命令服务接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface SkuInfoCommandService {

    /**
     * 保存或更新
     * @param skuInfoCommand
     */
    Long saveOrUpdate(SkuInfoCommand skuInfoCommand);

    /**
     * 删除，根据传递过来的skuId列表
     * @param skuIds skuId列表
     * @return
     */
    boolean delete(Collection<Long> skuIds);

    /**
     * 删除，根据传递过来的skuId
     * @param skuId skuId
     * @return
     */
    boolean delete(Long skuId);

    /**
     * 批量进行保存或更新
     * @param skuInfoCommandList
     * @return
     */
    Long[] batchSaveOrUpdate(List<SkuInfoCommand> skuInfoCommandList);
}
