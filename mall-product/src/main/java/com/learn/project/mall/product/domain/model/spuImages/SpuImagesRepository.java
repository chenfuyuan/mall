package com.learn.project.mall.product.domain.model.spuImages;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * spu图片-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface SpuImagesRepository {

    /**
     * 通过id查找
     * @param id id
     * @return
     */
    SpuImages find(SpuImagesId id);

    /**
     * 存储领域对象
     * @param spuImages
     */
    SpuImagesId store(SpuImages spuImages);

    /**
     * 根据传递过来的id进行删除
     * @param spuImagesIdList id列表
     * @return
     */
    boolean remove(Collection<SpuImagesId> spuImagesIdList);

    /**
     * 根据id进行删除
     * @param spuImagesId id
     * @return
     */
    boolean remove(SpuImagesId spuImagesId);

    Set<SpuImagesId> store(List<SpuImages> spuImagesList);
}
