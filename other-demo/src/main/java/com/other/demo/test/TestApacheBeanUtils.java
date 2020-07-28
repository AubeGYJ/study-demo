package com.other.demo.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * org.apache.commons.beanutils.BeanUtils
 * 浅拷贝：对基本数据类型进行值传递，对引用数据类型进行引用传递般的拷贝，此为浅拷贝
 * 深拷贝：对基本数据类型进行值传递，对引用数据类型，创建一个新的对象，并复制其内容，此为深拷贝
 *
 * @author guoyj
 * @date 2020/7/23 9:54
 */
public class TestApacheBeanUtils {
	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
		//下面只是用于单独测试
		PersonSource personSource = new PersonSource(1, "pjmike", "12345", 21);
		PersonDest personDest = new PersonDest();
		BeanUtils.copyProperties(personDest, personSource);
		System.out.println(JSON.toJSONString(personDest));

	}

}

class PersonSource {
	private Integer id;
	private String username;
	private String password;
	private Integer age;

	public PersonSource(Integer id, String username, String password, Integer age) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}

class PersonDest {
	private Integer id;
	private String username;
	private Integer age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "PersonDest{" +
			"id=" + id +
			", username='" + username + '\'' +
			", age=" + age +
			'}';
	}
}

