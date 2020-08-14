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
public class TestVO {
	/** 用户编号 **/
	private Integer userId;
	/** 用户名 **/
	private String username;
	/** 密码 **/
	private String password;
	/** 对应 TestBO.test.age **/
	private Integer age;
	/** 出生日期 **/
	private Date birth;
	/** 出生日期 **/
	private String  birthDateFormat;
	/**
	 * 对 BO 里面的字段(birthDay)进行拓展,expression 的形式
	 */
	private String birthExpressionFormat;
}
