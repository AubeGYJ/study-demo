package com.other.demo.beanconvert;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author guoyj
 * @date 2020/8/11 15:19
 */
@Data
@Accessors(chain = true)
public class TestDO {
	/** 用户编号 **/
	private Integer id;
	/** 用户名 **/
	private String username;
	/** 密码 **/
	private String password;
	/** 出生日期 **/
	private Date birthday;
	/** 角色id **/
	private String roleIds;
}
