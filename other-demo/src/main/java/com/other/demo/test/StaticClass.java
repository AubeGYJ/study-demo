package com.other.demo.test;

/**
 * 测试类
 *
 * @author guoyj
 * @date 2020/7/15 10:32
 */
public class StaticClass {
	final static StaticClass TEST = new StaticClass(2.8);
	static double x = 10.0;
	public double z;

	public StaticClass(double y) {
		z = x - y;
	}

	public static void main(String[] args) {
		System.out.println(StaticClass.TEST.z);
		System.out.println(new StaticClass(2.8).z);
	}
}
