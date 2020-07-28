package com.other.demo.test;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * stream中Collectors的用法
 * https://juejin.im/post/5f1e10d86fb9a07eb90cd439?utm_source=gold_browser_extension#heading-42
 *
 * @author guoyj
 * @date 2020/7/27 18:21
 */
@Slf4j
public class StreamCollectClass {
	private static List<String> list = Arrays.asList("jack", "bob", "alice", "mark");
	private static List<String> duplicateList = Arrays.asList("jack", "jack", "alice", "mark");

	public static void main(String[] args) {
		// stream转换为ArrayList
		List<String> listResult = list.stream().collect(Collectors.toList());
		log.info("{}", listResult);

		// stream转换为HashSet
		Set<String> setResult = list.stream().collect(Collectors.toSet());
		log.info("{}", setResult);
		Set<String> duplicateSetResult = duplicateList.stream().collect(Collectors.toSet());
		log.info("{}", duplicateSetResult);

		//转换为指定类型数据
		List<String> custListResult = list.stream().collect(Collectors.toCollection(LinkedList::new));
		log.info("{}", custListResult);

		//list转换为map
		Map<String, Integer> mapResult = list.stream().collect(Collectors.toMap(Function.identity(), String::length));
		log.info("{}", mapResult);
		// 如果list中有重复数据,则转换会报错 IllegalStateException todo
		Map<String, Integer> duplicateMapResult = duplicateList.stream().collect(Collectors.toMap(Function.identity(), String::length));
		// 修改为
		Map<String, Integer> duplicateMapResult2 = duplicateList.stream()
			.collect(Collectors.toMap(Function.identity(), String::length, (item, identicalItem) -> item));
		log.info("{}", duplicateMapResult2);

		// 对生成的集合在做一次操作
		List<String> collectAndThenResult = list.stream()
			.collect(Collectors.collectingAndThen(Collectors.toList(), l -> {
				return new ArrayList<>(l);
			}));
		log.info("{}", collectAndThenResult);

		//joining 连接stream中的元素
		String joinResult = list.stream().collect(Collectors.joining());
		log.info("{}", joinResult);
		String joinResult1 = list.stream().collect(Collectors.joining(" "));
		log.info("{}", joinResult1);
		String joinResult2 = list.stream().collect(Collectors.joining(" ", "prefix", "suffix"));
		log.info("{}", joinResult2);

		// 计算stream中元素的个数
		Long countResult = list.stream().collect(Collectors.counting());
		log.info("{}", countResult);

		// 分组
		Map<Integer, Set<String>> groupByResult = list.stream()
			.collect(Collectors.groupingBy(String::length, Collectors.toSet()));
		log.info("{}", groupByResult);

		Map<Boolean, List<String>> partitionResult = list.stream()
			.collect(Collectors.partitioningBy(s -> s.length() > 3));
		log.info("{}", partitionResult);


		// Collectors.summarizingDouble/Long/Int() 统计/和/最小/平均/最大
		// Collectors.averagingDouble/Long/Int() 求平均数
		// Collectors.summingDouble/Long/Int()  求和
		// Collectors.maxBy()/minBy()  获取最大/最小值

	}

}
