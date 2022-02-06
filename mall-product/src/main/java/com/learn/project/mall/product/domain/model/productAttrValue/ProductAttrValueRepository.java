package com.learn.project.mall.product.domain.model.productAttrValue;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * spu属性值-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface ProductAttrValueRepository {

    /**
     * 通过id查找
     * @param id id
     * @return
     */
    ProductAttrValue find(ProductAttrValueId id);

    /**
     * 存储领域对象
     * @param productAttrValue
     */
    ProductAttrValueId store(ProductAttrValue productAttrValue);

    /**
     * 根据传递过来的id进行删除
     * @param productAttrValueIdList id列表
     * @return
     */
    boolean remove(Collection<ProductAttrValueId> productAttrValueIdList);

    /**
     * 根据id进行删除
     * @param productAttrValueId id
     * @return
     */
    boolean remove(ProductAttrValueId productAttrValueId);

    Set<ProductAttrValueId> store(List<ProductAttrValue> productAttrValueList);
}
