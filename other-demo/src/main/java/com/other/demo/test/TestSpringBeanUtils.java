package com.other.demo.test;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * org.springframework.beans.BeanUtils
 * 浅拷贝：对基本数据类型进行值传递，对引用数据类型进行引用传递般的拷贝，此为浅拷贝
 * 深拷贝：对基本数据类型进行值传递，对引用数据类型，创建一个新的对象，并复制其内容，此为深拷贝
 *
 * @author guoyj
 * @date 2020/7/23 9:58
 */
public class TestSpringBeanUtils {
	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

		//下面只是用于单独测试
		PersonSource personSource = new PersonSource(1, "pjmike", "12345", 21, new Date());
		PersonDest personDest = new PersonDest();
		BeanUtils.copyProperties(personSource, personDest);
		System.out.println("persondest: " + personDest);
	}
}
