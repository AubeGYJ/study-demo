package com.thread.demo;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author guoyj
 * @Date 2020/6/27 22:54
 */
public class ExtendedExecutor extends ThreadPoolExecutor {


	public ExtendedExecutor() {
		super(5, 10, 10, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1000));
	}

	// 这可是jdk文档里面给的例子。。
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		if (t == null && r instanceof Future<?>) {
			try {
				Object result = ((Future<?>) r).get();
			} catch (CancellationException ce) {
				t = ce;
			} catch (ExecutionException ee) {
				t = ee.getCause();
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt(); // ignore/reset
			}
		}
		if (t != null)
			System.out.println(t);
	}
}
