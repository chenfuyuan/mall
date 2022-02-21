package com.learn.project.mall.product.application;

import com.learn.project.mall.product.application.command.CategoryBrandRelationCommand;

import java.util.Collection;
import java.util.List;

/**
 * 品牌分类关联-命令服务接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface CategoryBrandRelationCommandService {

    /**
     * 保存或更新
     * @param categoryBrandRelationCommand
     */
    Long saveOrUpdate(CategoryBrandRelationCommand categoryBrandRelationCommand);

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
     * @param categoryBrandRelationCommandList
     * @return
     */
    Long[] batchSaveOrUpdate(List<CategoryBrandRelationCommand> categoryBrandRelationCommandList);

    /**
     * 添加 分类与品牌关联详情
     * @param categoryBrandRelationCommand
     * @return
     */
    Long addDetail(CategoryBrandRelationCommand categoryBrandRelationCommand);
}
