package com.august.core.exception;

import lombok.Data;

@Data
public class ParameterException extends RuntimeException {

    private Integer code;
    private String msg;

    public ParameterException(Integer code,String msg) {
        super(msg);
        this.code = code;
    }
}
