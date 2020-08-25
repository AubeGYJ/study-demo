package com.other.demo.spel;

/**
 * @author guoyj
 * @date 2020/8/24 17:58
 */
public class Account {


	private String name;
	private int footballCount;
	private Friend friend;

	public Account(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setFootballCount(int footballCount) {
		this.footballCount = footballCount;
	}

	public void addFriend(Friend friend) {

		this.friend = friend;
	}

	public int getFootballCount() {
		return footballCount;
	}

	public Friend getFriend() {
		return friend;
	}
}

class Friend {
	private String name;

	public Friend(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}