package com.learn.project.mall.thirdparty.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 * @author chenfuyuan
 * @date 2022/1/6 15:45
 */
@Component
@Data
public class AliYunOssConfig {
    /**
     * access-key
     */
    @Value("${alibaba.cloud.access-key}")
    private String accessId;

    /**
     * secret-key
     */
    @Value("${alibaba.cloud.secret-key}")
    private String accessKey;

    @Value("${alibaba.cloud.oss.endpoint}")
    private String endpoint;

    @Value("${config.oss.bucket}")
    private String bucketName;

    @Value("http://${config.oss.bucket}.${alibaba.cloud.oss.endpoint}")
    private String host;

    /**
     * 超时时间(默认为30秒)
     */
    @Value("${config.oss.expire-time}")
    private long expireTime;

    /**
     * 上传文件最大大小
     */
    @Value("${config.oss.content-length-range}")
    private long contentLengthRange;

    /**
     * 获取超时时间
     * @return
     */
    public Date getExpiration(){
        long expireEndTime = System.currentTimeMillis() + expireTime;
        Date expiration = new Date(expireEndTime);
        return expiration;
    }

    @Override
    public String toString() {
        return "AliYunOssConfig{" +
                "accessId='" + accessId + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", host='" + host + '\'' +
                ", expireTime=" + expireTime +
                ", contentLengthRange=" + contentLengthRange +
                '}';
    }
}
