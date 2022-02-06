package com.learn.project.mall.product.domain.model.spuComment;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 商品评价-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
public interface SpuCommentRepository {

    /**
     * 通过id查找
     * @param id id
     * @return
     */
    SpuComment find(SpuCommentId id);

    /**
     * 存储领域对象
     * @param spuComment
     */
    SpuCommentId store(SpuComment spuComment);

    /**
     * 根据传递过来的id进行删除
     * @param spuCommentIdList id列表
     * @return
     */
    boolean remove(Collection<SpuCommentId> spuCommentIdList);

    /**
     * 根据id进行删除
     * @param spuCommentId id
     * @return
     */
    boolean remove(SpuCommentId spuCommentId);

    Set<SpuCommentId> store(List<SpuComment> spuCommentList);
}
