package com.learn.project.mall.product.domain.model.brand;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 品牌-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface BrandRepository {

    /**
     * 通过brandId查找
     * @param brandId id
     * @return
     */
    Brand find(BrandId brandId);

    /**
     * 存储领域对象
     * @param brand
     */
    BrandId store(Brand brand);

    /**
     * 根据传递过来的id进行删除
     * @param brandIdList id列表
     * @return
     */
    boolean remove(Collection<BrandId> brandIdList);

    /**
     * 根据id进行删除
     * @param brandId id
     * @return
     */
    boolean remove(BrandId brandId);

    Set<BrandId> store(List<Brand> brandList);
}
