package com.learn.project.mall.thirdparty.service;

import com.learn.project.mall.thirdparty.domain.AliYunOssSignature;
import com.learn.project.mall.thirdparty.domain.OssSignature;

import java.util.Map;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/1/6 15:18
 */
public interface OssService {

    /**
     * 获取签名
     * @return
     */
    OssSignature getSignature();
}
