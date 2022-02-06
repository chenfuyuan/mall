package com.learn.project.mall.product.application;

import com.learn.project.mall.product.application.command.BrandCommand;

import java.util.Collection;
import java.util.List;

/**
 * 品牌-命令服务接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface BrandCommandService {

    /**
     * 保存或更新
     * @param brandCommand
     */
    Long saveOrUpdate(BrandCommand brandCommand);

    /**
     * 删除，根据传递过来的brandId列表
     * @param brandIds brandId列表
     * @return
     */
    boolean delete(Collection<Long> brandIds);

    /**
     * 删除，根据传递过来的brandId
     * @param brandId brandId
     * @return
     */
    boolean delete(Long brandId);

    /**
     * 批量进行保存或更新
     * @param brandCommandList
     * @return
     */
    Long[] batchSaveOrUpdate(List<BrandCommand> brandCommandList);
}
