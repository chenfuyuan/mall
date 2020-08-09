package com.learn.common.constant;

import lombok.Getter;

/**
 * @EnumName BizCode
 * @Description 响应代码
 * @Author chenfuyuan
 * @Date 2020-8-8 14:26
 * @Version 1.0
 */
@Getter
public enum BizCode {

    /**
     * 系统未知异常
     */
    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    /**
     * 参数校验失败异常
     */
    VALID_EXCEPTION(10001,"参数格式校验失败");

    /**
     * 状态码
     */
    int code;
    /**
     * 状态信息
     */
    String message;

    BizCode(int code,String message){
        this.code = code;
        this.message = message;
    }


}
