package com.learn.project.mall.product.domain.model.skuSaleAttrValue;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * sku销售属性&值-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface SkuSaleAttrValueRepository {

    /**
     * 通过id查找
     * @param id id
     * @return
     */
    SkuSaleAttrValue find(SkuSaleAttrValueId id);

    /**
     * 存储领域对象
     * @param skuSaleAttrValue
     */
    SkuSaleAttrValueId store(SkuSaleAttrValue skuSaleAttrValue);

    /**
     * 根据传递过来的id进行删除
     * @param skuSaleAttrValueIdList id列表
     * @return
     */
    boolean remove(Collection<SkuSaleAttrValueId> skuSaleAttrValueIdList);

    /**
     * 根据id进行删除
     * @param skuSaleAttrValueId id
     * @return
     */
    boolean remove(SkuSaleAttrValueId skuSaleAttrValueId);

    Set<SkuSaleAttrValueId> store(List<SkuSaleAttrValue> skuSaleAttrValueList);
}
