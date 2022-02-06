package com.learn.project.mall.product.domain.model.spuInfo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * spu信息-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface SpuInfoRepository {

    /**
     * 通过id查找
     * @param id id
     * @return
     */
    SpuInfo find(SpuInfoId id);

    /**
     * 存储领域对象
     * @param spuInfo
     */
    SpuInfoId store(SpuInfo spuInfo);

    /**
     * 根据传递过来的id进行删除
     * @param spuInfoIdList id列表
     * @return
     */
    boolean remove(Collection<SpuInfoId> spuInfoIdList);

    /**
     * 根据id进行删除
     * @param spuInfoId id
     * @return
     */
    boolean remove(SpuInfoId spuInfoId);

    Set<SpuInfoId> store(List<SpuInfo> spuInfoList);
}
