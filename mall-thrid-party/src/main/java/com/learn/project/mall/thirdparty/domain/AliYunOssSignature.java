package com.learn.project.mall.thirdparty.domain;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.aliyun.oss.model.PolicyConditions;
import lombok.Data;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author chenfuyuan
 * @date 2022/1/6 15:36
 */
@Data
public class AliYunOssSignature extends OssSignature{
    /**
     * 用户请求的AccessKey ID
     */
    private String accessId;
    /**
     * 用户发送上传请求的域名
     */
    private String host;

    /**
     * 用户表单上传的策略 Policy为经过Base64编码过的字符串
     */
    private String policy;

    /**
     * 对Policy签名后的字符串
     */
    private String signature;

    /**
     * 由服务器端指定的Policy过期时间，格式为Unix时间戳（自UTC时间1970年01月01号开始的秒数）。
     */
    private String expire;

    /**
     * 限制上传的文件前缀
     */
    private String dir;


}
