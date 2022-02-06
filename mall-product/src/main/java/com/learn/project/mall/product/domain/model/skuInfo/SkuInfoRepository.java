package com.learn.project.mall.product.domain.model.skuInfo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * sku信息-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface SkuInfoRepository {

    /**
     * 通过skuId查找
     * @param skuId id
     * @return
     */
    SkuInfo find(SkuInfoId skuId);

    /**
     * 存储领域对象
     * @param skuInfo
     */
    SkuInfoId store(SkuInfo skuInfo);

    /**
     * 根据传递过来的id进行删除
     * @param skuInfoIdList id列表
     * @return
     */
    boolean remove(Collection<SkuInfoId> skuInfoIdList);

    /**
     * 根据id进行删除
     * @param skuInfoId id
     * @return
     */
    boolean remove(SkuInfoId skuInfoId);

    Set<SkuInfoId> store(List<SkuInfo> skuInfoList);
}
