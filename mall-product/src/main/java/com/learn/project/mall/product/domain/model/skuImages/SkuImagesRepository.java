package com.learn.project.mall.product.domain.model.skuImages;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * sku图片-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface SkuImagesRepository {

    /**
     * 通过id查找
     * @param id id
     * @return
     */
    SkuImages find(SkuImagesId id);

    /**
     * 存储领域对象
     * @param skuImages
     */
    SkuImagesId store(SkuImages skuImages);

    /**
     * 根据传递过来的id进行删除
     * @param skuImagesIdList id列表
     * @return
     */
    boolean remove(Collection<SkuImagesId> skuImagesIdList);

    /**
     * 根据id进行删除
     * @param skuImagesId id
     * @return
     */
    boolean remove(SkuImagesId skuImagesId);

    Set<SkuImagesId> store(List<SkuImages> skuImagesList);
}
