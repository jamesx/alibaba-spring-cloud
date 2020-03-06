package com.august.user.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDto {

    @Length(min = 3,max = 18,message = "用户名必须是8到18位")
    private String userName;

    private String company;
}
