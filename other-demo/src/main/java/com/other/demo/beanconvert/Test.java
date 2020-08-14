package com.other.demo.beanconvert;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author guoyj
 * @date 2020/8/11 16:42
 */
@Data
@Accessors(chain = true)
public class Test {

	/** 年龄 **/
	private Integer age;
}
