package com.other.demo.test;

import cn.hutool.core.util.ObjectUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * bug查找
 *
 * @author guoyj
 * @date 2020/7/27 10:24
 */
public class Monitor {
	/**
	 * 此类缺少equals和hashCode重写的方法
	 */
	public static class MonitorKey {
		String key;
		String desc;

		public MonitorKey(String key, String desc) {
			this.key = key;
			this.desc = desc;
		}
	}

	public static class MonitorValue {
		AtomicInteger count = new AtomicInteger();
		float avgTime;
		AtomicLong totalTime = new AtomicLong();
	}

	private Map<MonitorKey, MonitorValue> monitors = new ConcurrentHashMap<>();

	/**
	 * 此方法不符合一致性原则
	 *
	 * @param url
	 * @param desc
	 * @param timeCost
	 */
	public void visit(String url, String desc, long timeCost) {
		MonitorKey key = new MonitorKey(url, desc);
		MonitorValue value = monitors.get(key);
		if (ObjectUtil.isNull(value)) {
			value = new MonitorValue();
			monitors.put(key, value);
		}
		value.count.getAndIncrement();
		value.totalTime.getAndAdd(timeCost);
		value.avgTime = value.totalTime.get() / value.count.get();
	}
}
