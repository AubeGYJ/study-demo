package com.thread.demo;

import com.demo.util.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 提交任务
 * 如果线程数少于核心线程，创建核心线程执行任务
 * 如果线程数等于核心线程，把任务添加到LinkedBlockingQueue阻塞队列
 * 如果线程执行完任务，去阻塞队列取任务，继续执行。
 * <p>
 * LinkedBlockingQueue（可设置容量队列）基于链表结构的阻塞队列，按FIFO排序任务，
 * 容量可以选择进行设置，不设置的话，将是一个无边界的阻塞队列，最大长度为Integer.MAX_VALUE，吞吐量通常要高于ArrayBlockingQuene
 *
 * @Author guoyj
 * @Date 2020/6/27 18:56
 */
public class NewFixedThreadPool {

	private static void method1() {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			executor.execute(() -> {
				try {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + "正在执行");
				} catch (InterruptedException e) {
					System.out.println("程序出错啦!!!");
				}
			});
		}
		while (true) {
			// 判断线程池中任务是否全部执行完毕  若执行完毕 再返回
			if (executor.isTerminated()) {
				break;
			}
		}
		System.out.println("线程池任务执行完毕!!!");
	}

	private static void method2() {
		ExecutorService executor = ThreadPoolUtil.newFixedThreadPool(5);
		for (int i = 0; i < 20; i++) {
			executor.execute(() -> {
				try {
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + "正在执行");
				} catch (Exception e) {
					System.out.println("程序出错啦!!!");
				}
			});
		}
		while (true) {
			// 判断线程池中任务是否全部执行完毕  若执行完毕 再返回
			if (executor.isTerminated()) {
				break;
			}
		}
		System.out.println("线程池任务执行完毕!!!");
	}

	private static void method3() {
//		ExecutorService executor = Executors.newFixedThreadPool(5);
		ExecutorService executor = ThreadPoolUtil.newFixedThreadPool(3, 2);
		for (int i = 0; i < 10; i++) {
			executor.execute(() -> {
				try {
					Thread.sleep(5000);
					System.out.println(Thread.currentThread().getName() + "正在执行");
				} catch (Exception e) {
					System.out.println("程序出错啦!!!");
				}
			});
		}
		// 关闭线程池
		System.out.println("线程池关闭, time" + System.currentTimeMillis());
		executor.shutdown();
		executor.execute(() -> {
			try {
				Thread.sleep(5000);
				System.out.println(Thread.currentThread().getName() + "正在执行");
			} catch (Exception e) {
				System.out.println("程序出错啦!!!");
			}
		});

//		executor.shutdownNow();
		while (true) {
			// 判断线程池中任务是否全部执行完毕  若执行完毕 再返回
			if (executor.isTerminated()) {
				System.out.println("线程池状态校验!!!");
				break;
			}
		}
		System.out.println("线程池任务执行完毕, time" + System.currentTimeMillis());
	}


	public static void main(String[] args) {

//		method1();
//		method2();
		method3();
	}
}
