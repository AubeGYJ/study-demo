package com.other.demo.exception;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author guoyj
 * @date 2020/8/24 18:25
 */
public class TryCatchDemo {

	public static void main(String[] args) {
		try {
			Class<?> className1 = Class.forName("TryCatchDemo");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Class<?> className2 = classsFind(o -> Class.forName(o), "TryCatchDemo");

		// 方法一
		FileWriter fw1 = null;
		try {
			fw1 = new FileWriter("test\\test.txt");
			fw1.write("test");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// 方法二
		try (FileWriter fw = new FileWriter("test.txt")) {
			fw.write("test");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}


	public static Class classsFind(ClassFindInterface classFindInterface, String className) {
		Class<?> clazz = null;
		try {
			clazz = classFindInterface.classNametoClass(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clazz;
	}
}
