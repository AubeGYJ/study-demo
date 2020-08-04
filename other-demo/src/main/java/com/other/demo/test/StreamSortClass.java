package com.other.demo.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * stream排序(对象的多个属性)
 *https://www.cnblogs.com/kuanglongblogs/p/11230250.html
 *
 * @author guoyj
 * @date 2020/7/23 18:34
 */
public class StreamSortClass {

	public static void main(String[] args) {
		List<test> testList = new ArrayList<>();
		testList.add(new test(1, "2020-07-23 19:01"));
		testList.add(new test(2, "2020-07-23 19:01"));
		testList.add(new test(3, "2020-07-23 19:02"));
		testList.add(new test(1, "2020-07-23 17:01"));
		testList.add(new test(3, "2020-07-23 19:01"));
		testList.add(new test(1, "2020-07-23 18:01"));
		testList.add(new test(4, "2020-07-23 19:01"));
		testList.add(new test(5, "2020-07-23 19:01"));

		List<test> sort =
			testList.stream().sorted(Comparator.comparing(test::getTime).thenComparing(test::getState).reversed()).collect(toList());
		System.out.println("------------------------------------");
		sort.forEach(o -> {
			System.out.println(o.toString());
		});


	}
}

class test {
	//状态
	private int state;
	//时间
	private String time;

	public test(int state, String time) {
		this.state = state;
		this.time = time;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "test{" +
			"state=" + state +
			", time=" + time +
			'}';
	}
}
