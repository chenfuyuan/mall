package com.learn.project.mall.product.domain.model.common;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 通用测试-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface CommonRepository {

    /**
     * 通过commonId查找
     * @param commonId id
     * @return
     */
    Common find(CommonId commonId);

    /**
     * 存储领域对象
     * @param common
     */
    CommonId store(Common common);

    /**
     * 根据传递过来的id进行删除
     * @param commonIdList id列表
     * @return
     */
    boolean remove(Collection<CommonId> commonIdList);

    /**
     * 根据id进行删除
     * @param commonId id
     * @return
     */
    boolean remove(CommonId commonId);

    Set<CommonId> store(List<Common> commonList);
}
