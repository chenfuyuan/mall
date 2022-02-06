package com.learn.project.mall.product.application;

import com.learn.project.mall.product.application.command.AttrAttrgroupRelationCommand;

import java.util.Collection;
import java.util.List;

/**
 * 属性&属性分组关联-命令服务接口
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface AttrAttrgroupRelationCommandService {

    /**
     * 保存或更新
     * @param attrAttrgroupRelationCommand
     */
    Long saveOrUpdate(AttrAttrgroupRelationCommand attrAttrgroupRelationCommand);

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
     * @param attrAttrgroupRelationCommandList
     * @return
     */
    Long[] batchSaveOrUpdate(List<AttrAttrgroupRelationCommand> attrAttrgroupRelationCommandList);
}
