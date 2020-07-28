package com.thread.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 提交任务
 * 因为没有核心线程，所以任务直接加到SynchronousQueue队列。
 * 判断是否有空闲线程，如果有，就去取出任务执行。
 * 如果没有空闲线程，就新建一个线程执行。
 * 执行完任务的线程，还可以存活60秒，如果在这期间，接到任务，可以继续活下去；否则，被销毁。
 * <p>
 * SynchronousQueue（同步队列）一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，
 * 否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQuene
 *
 * @Author guoyj
 * @Date 2020/6/27 18:56
 */
public class NewCachedThreadPool {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			executor.execute(() -> {
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "正在执行");
			});
		}
		// 关闭线程池
		executor.shutdown();
		while (true) {
			// 判断线程池中任务是否全部执行完毕  若执行完毕 再返回
			if (executor.isTerminated()) {
				break;
			}
		}
		System.out.println("线程池任务执行完毕!!!");
	}
}
