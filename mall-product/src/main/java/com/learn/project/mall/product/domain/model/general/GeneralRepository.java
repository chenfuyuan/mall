package com.learn.project.mall.product.domain.model.general;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 通用测试-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public interface GeneralRepository {

    /**
     * 通过id查找
     * @param id id
     * @return
     */
    General find(GeneralId id);

    /**
     * 存储领域对象
     * @param general
     */
    GeneralId store(General general);

    /**
     * 根据传递过来的id进行删除
     * @param generalIdList id列表
     * @return
     */
    boolean remove(Collection<GeneralId> generalIdList);

    /**
     * 根据id进行删除
     * @param generalId id
     * @return
     */
    boolean remove(GeneralId generalId);

    Set<GeneralId> store(List<General> generalList);
}
