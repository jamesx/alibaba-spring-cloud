package com.august.user.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDto {

    @Length(min = 8,max = 18,message = "用户名必须是8到18位")
    private String userName;

    @Length(min = 8,max = 18,message = "公司名必须是8到18位")
    private String company;
}
