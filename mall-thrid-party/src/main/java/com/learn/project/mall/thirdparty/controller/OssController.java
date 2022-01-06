package com.learn.project.mall.thirdparty.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.learn.project.common.utils.R;
import com.learn.project.mall.thirdparty.domain.AliYunOssSignature;
import com.learn.project.mall.thirdparty.domain.OssSignature;
import com.learn.project.mall.thirdparty.service.OssService;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * OSSController
 *
 * @author chenfuyuan
 * @date 2022/1/6 14:46
 */
@RestController
public class OssController {

    @Autowired
    private OssService ossService;


    @RequestMapping("/oss/signature")
    public R singnature(){
        OssSignature signature = ossService.getSignature();
        return R.ok().put("signature", signature);
    }
}
