package com.other.demo.exception;

/**
 * @author guoyj
 * @date 2020/8/24 18:42
 */
@FunctionalInterface
public interface ClassFindInterface {
	Class<?> classNametoClass(String className) throws ClassNotFoundException;
}
