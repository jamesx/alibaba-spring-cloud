package com.august.user.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author august
 * @email 379249906@qq.com
 * @date 2020-04-02 17:14:23
 */
@Data
@TableName("tb_address")
public class AddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "address_id", type = IdType.ASSIGN_ID)
	private Long addressId;
	/**
	 * 
	 */
	private String cityName;
	/**
	 * 
	 */
	private String countryName;

}
