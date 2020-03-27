package com.august.core.exception;

import com.august.core.bean.ResultEnum;
import lombok.Data;

@Data
public class UnauthorizedException extends RuntimeException{
    private Integer code;

    public UnauthorizedException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
