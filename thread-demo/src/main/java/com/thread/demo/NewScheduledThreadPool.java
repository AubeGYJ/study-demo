package com.thread.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 添加一个任务
 * 线程池中的线程从 DelayQueue 中取任务
 * 线程从 DelayQueue 中获取 time 大于等于当前时间的task
 * 执行完后修改这个 task 的 time 为下次被执行的时间
 * 这个 task 放回DelayQueue队列中
 * <p>
 * DelayQueue（延迟队列）是一个任务定时周期的延迟执行的队列。根据指定的执行时间从小到大排序，否则根据插入到队列的先后排序
 *
 * @Author guoyj
 * @Date 2020/6/27 18:57
 */
public class NewScheduledThreadPool {

	private static void method1() {
		/**
		 创建一个给定初始延迟的间隔性的任务，之后的下次执行时间是上一次任务从执行到结束所需要的时间+* 给定的间隔时间
		 */
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.scheduleWithFixedDelay(() -> {
			System.out.println("current Time" + System.currentTimeMillis());
			System.out.println(Thread.currentThread().getName() + "正在执行");
		}, 1, 3, TimeUnit.SECONDS);
	}

	private static void method2() {
		/**
		 创建一个给定初始延迟的间隔性的任务，之后的每次任务执行时间为 初始延迟 + N * delay(间隔)
		 */
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			System.out.println("current Time" + System.currentTimeMillis());
			System.out.println(Thread.currentThread().getName() + "正在执行");
		}, 1, 3, TimeUnit.SECONDS);
		;

	}

	public static void main(String[] args) {

		method1();

		//method2();


	}
}
