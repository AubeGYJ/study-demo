package com.thread.demo.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池类
 *
 * @author guoyj
 */
public class ThreadPoolUtil {

	/**
	 * 线程池名称
	 */
	private static final String THREAD_NAME = "thread-tender-%d";
	/**
	 * 线程池维护线程的最少数量
	 */
	private final static int CORE_POOL_SIZE = 5;
	/**
	 * 线程池维护线程的最大数量
	 */
	private final static int MAX_POOL_SIZE = 500;
	/**
	 * 线程池维护线程的最大数量
	 */
	private final static int KEEP_ALIVE_TIME = 500;
	/**
	 * 线程池队列容量
	 */
	private final static int QUEUE_CAPACITY = 1000;

	/**
	 * 创建newFixedThreadPool线程池
	 *
	 * @param corePoolSize 线程池中核心线程数,默认开启5条
	 */
	public static ExecutorService newFixedThreadPool(Integer corePoolSize) {

		// 定义线程工厂
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("newFixedThreadPool-%d").build();
		if (Objects.isNull(corePoolSize)) {
			corePoolSize = CORE_POOL_SIZE;
		}
		// 创建线程池
		return new ThreadPoolExecutor(corePoolSize, corePoolSize, 0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<>(1), namedThreadFactory, new ThreadPoolExecutor.DiscardPolicy());
	}

	/**
	 * 创建newFixedThreadPool线程池
	 *
	 * @param corePoolSize 线程池中核心线程数,默认开启5条
	 */
	public static ExecutorService newFixedThreadPool(Integer corePoolSize, Integer queueSize) {

		// 定义线程工厂
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("newFixedThreadPool-%d").build();
		if (Objects.isNull(corePoolSize)) {
			corePoolSize = CORE_POOL_SIZE;
		}
		if (Objects.isNull(queueSize)) {
			queueSize = QUEUE_CAPACITY;
		}
		// 创建线程池
		return new ThreadPoolExecutor(corePoolSize, corePoolSize, 0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<>(queueSize), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
	}

	/**
	 * 创建newCachedThreadPool线程池
	 */
	public static ExecutorService newCachedThreadPool() {

		// 定义线程工厂
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("newCachedThreadPool-%d").build();
		// 创建线程池
		return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), namedThreadFactory);
	}

	/**
	 * 创建newSingleThreadExecutor线程池
	 */
	public static ExecutorService newSingleThreadExecutor() {

		// 定义线程工厂
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("newSingleThreadExecutor-%d").build();
		// 创建线程池
		return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(QUEUE_CAPACITY), namedThreadFactory);
	}


}
