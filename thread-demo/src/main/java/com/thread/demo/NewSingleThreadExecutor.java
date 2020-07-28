package com.thread.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 提交任务
 * 线程池是否有一条线程在，如果没有，新建线程执行任务
 * 如果有，讲任务加到LinkedBlockingQueue阻塞队列
 * 当前的唯一线程，从队列取任务，执行完一个，再继续取，一个人（一条线程）夜以继日地干活。
 *
 * @Author guoyj
 * @Date 2020/6/27 18:56
 */
public class NewSingleThreadExecutor {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			executor.execute(() -> {
				System.out.println(Thread.currentThread().getName() + "正在执行");
			});
		}

	}
}
