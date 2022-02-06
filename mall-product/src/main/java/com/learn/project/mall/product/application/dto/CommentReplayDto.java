package com.learn.project.mall.product.application.dto;

import lombok.Data;

import java.util.Date;

/**
 * 商品评价回复关系-数据传输对象
 *
 * @author chenfuyuan
 * @email chenfuyuan0713@163.com
 * @date 2022-02-06 21:50:38
 */
@Data
public class CommentReplayDto {

    /**
    * id
    */
    private Long id;
    /**
    * 评论id
    */
    private Long commentId;
    /**
    * 回复id
    */
    private Long replyId;
    /**
    * 是否删除[0-未删除, 1-删除]
    */
    private Integer isDelete;
    /**
    * 创建时间
    */
    private Date gmtCreate;
    /**
    * 修改时间
    */
    private Date gmtModified;
    /**
    * 更新版本
    */
    private Integer updateVersion;
}
