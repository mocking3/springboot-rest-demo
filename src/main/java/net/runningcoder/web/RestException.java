package net.runningcoder.web;

import lombok.Value;

@Value
public class RestException extends RuntimeException {

    private int errorCode;
    private String errorMsg;

    public RestException() {
        this.errorCode = RspCode.SYSTEM_ERROR.getCode();
        this.errorMsg = RspCode.SYSTEM_ERROR.getMessage();
    }

    public RestException(RspCode rspCode) {
        this.errorCode = rspCode.getCode();
        this.errorMsg = rspCode.getMessage();
    }

}
