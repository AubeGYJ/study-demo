package com.other.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * stream 流数据转换
 *
 * @author guoyj
 * @date 2020/7/15 11:18
 */
public class StreamClass {
	private List<Integer> initList() {
		List<Integer> intList = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			intList.add(new Random().nextInt(200));
		}
		return intList;
	}

	private List<User> initUser() {
		List<User> initUser = new ArrayList<>();
		for (int i = 1; i < 50; i++) {
			initUser.add(new User(i, "李四" + i, new Random().nextInt(5)));
		}
		return initUser;
	}

	public static void main(String[] args) {

	}
}

class User {
	Integer userId;
	String userName;
	Integer userRoleId;

	public User(int userId, String userName, int userRoleId) {
		this.userId = userId;
		this.userName = userName;
		this.userRoleId = userRoleId;
	}
}

