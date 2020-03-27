package com.august.core.bean;

public enum ResultEnum {
    UNAUTHORIZED(Constant.UNAHTUENTICATED,"权限不足"),

    UNAHTUENTICATED(Constant.UNAHTUENTICATED,"认证失败"),
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
