package com.learn.project.mall.product.application;

import java.util.Collection;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/2/4 16:25
 */
public interface CategoryCommandService {

    /**
     * 根据catId删除指定的分类
     * @param catIds 需要删除的分类id
     * @return 是否删除成功
     */
    boolean removeCategoryByIds(Collection<Long> catIds);

}
