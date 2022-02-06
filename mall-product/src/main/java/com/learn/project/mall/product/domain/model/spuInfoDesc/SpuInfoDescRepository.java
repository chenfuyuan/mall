package com.learn.project.mall.product.domain.model.spuInfoDesc;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * spu信息介绍-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public interface SpuInfoDescRepository {

    /**
     * 通过spuId查找
     * @param spuId id
     * @return
     */
    SpuInfoDesc find(SpuInfoDescId spuId);

    /**
     * 存储领域对象
     * @param spuInfoDesc
     */
    SpuInfoDescId store(SpuInfoDesc spuInfoDesc);

    /**
     * 根据传递过来的id进行删除
     * @param spuInfoDescIdList id列表
     * @return
     */
    boolean remove(Collection<SpuInfoDescId> spuInfoDescIdList);

    /**
     * 根据id进行删除
     * @param spuInfoDescId id
     * @return
     */
    boolean remove(SpuInfoDescId spuInfoDescId);

    Set<SpuInfoDescId> store(List<SpuInfoDesc> spuInfoDescList);
}
