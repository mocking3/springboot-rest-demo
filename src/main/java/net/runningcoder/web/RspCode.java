package net.runningcoder.web;

/**
 * Created by wangmaocheng on 2017/4/5.
 */
public enum RspCode {
    SYSTEM_ERROR(10001, "服务器内部错误"),

    ERROR_METHOD(10002, "错误的请求方式（GET/POST/PUT/DELETE）"),

    PARAMS_LOST(10003, "请求参数缺失"),

    PARAMS_NOT_VALID(10004, "存在不合法的请求参数"),

    RESOURCE_NOT_EXIST(10005, "资源不存在"),

    DATA_OUT_OF_THRESHOLD(10006, "数据超过阈值"),

    FREQUENT_OPERATION(10007, "操作频繁"),

    REPEAT_OPERATION(10008, "重复操作"),

    ILLEGAL_REQUEST(10009, "非法请求"),



    GET_TOKEN_ERROR(20001, "获取token失败（用户不存在或者用户名密码错误或者用户被禁用）"),

    TOKEN_AUTH_NOT_VALID(20002, "无效的token认证"),

    TOKEN_AUTH_EXPIRED(20003, "token认证过期"),

    PERMISSION_DENIED(20004, "权限不足"),

    ;

    private int code;
    private String message;

    RspCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
