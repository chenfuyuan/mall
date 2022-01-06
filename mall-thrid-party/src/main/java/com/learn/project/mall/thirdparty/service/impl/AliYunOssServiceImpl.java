package com.learn.project.mall.thirdparty.service.impl;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.learn.project.common.utils.GlobalConstant;
import com.learn.project.mall.thirdparty.domain.AliYunOssConfig;
import com.learn.project.mall.thirdparty.domain.AliYunOssSignature;
import com.learn.project.mall.thirdparty.service.OssService;
import com.uptool.core.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/1/6 15:42
 */
@Service
public class AliYunOssServiceImpl implements OssService {
    @Autowired
    OSS ossClient;

    @Autowired
    AliYunOssConfig aliYunOssConfig;
    @Override
    public AliYunOssSignature getSignature() {
        AliYunOssSignature result = new AliYunOssSignature();
        try {
            String dir = TimeUtil.todayNoTimeToStr() + "/";

            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, aliYunOssConfig.getContentLengthRange());
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            //获取超时时间
            Date expiration = aliYunOssConfig.getExpiration();
            //根据AliYunOss配置生成策略
            String postPolicy = ossClient.generatePostPolicy(expiration,policyConds);
            //根据策略生成签名
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            //进行封装

            byte[] binaryData = postPolicy.getBytes("utf-8");
            //进行Base64编码
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            result.setPolicy(encodedPolicy);
            result.setDir(dir);
            result.setAccessId(aliYunOssConfig.getAccessId());
            result.setHost(aliYunOssConfig.getHost());
            result.setExpire(String.valueOf(expiration.getTime() / 1000));
            result.setSignature(postSignature);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ossClient.shutdown();
        }
    }
}
