package com.learn.project.mall.product.domain.model.commentReplay;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 商品评价回复关系-仓储服务
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 20:41:32
 */
public interface CommentReplayRepository {

    /**
     * 通过id查找
     * @param id id
     * @return
     */
    CommentReplay find(CommentReplayId id);

    /**
     * 存储领域对象
     * @param commentReplay
     */
    CommentReplayId store(CommentReplay commentReplay);

    /**
     * 根据传递过来的id进行删除
     * @param commentReplayIdList id列表
     * @return
     */
    boolean remove(Collection<CommentReplayId> commentReplayIdList);

    /**
     * 根据id进行删除
     * @param commentReplayId id
     * @return
     */
    boolean remove(CommentReplayId commentReplayId);

    Set<CommentReplayId> store(List<CommentReplay> commentReplayList);
}
