package com.learn.project.common.web.exception;

import com.learn.project.common.web.constant.BaseErrorInfoInterface;
import com.learn.project.common.web.constant.ResponseCodeEnum;

/**
 * 不是系统异常，抛出错误500，报告系统错
 * @author chenfuyuan
 * @date 2022/2/4 16:52
 */
public class NoBizException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public NoBizException() {
        super();
        this.errorCode = ResponseCodeEnum.INTERNAL_SERVER_ERROR.getResultCode();
        this.errorMsg = ResponseCodeEnum.INTERNAL_SERVER_ERROR.getResultMsg();
    }

    public NoBizException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface.getResultCode());
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    public NoBizException(BaseErrorInfoInterface errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getResultCode(), cause);
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    public NoBizException(String errorMsg) {
        super(errorMsg);
        this.errorCode = ResponseCodeEnum.INTERNAL_SERVER_ERROR.getResultCode();
        this.errorMsg = errorMsg;
    }

    public NoBizException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public NoBizException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
