package com.other.demo.string;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 字符串的一些操作
 * https://juejin.im/post/6865040394229547016?utm_source=gold_browser_extension
 *
 * @author guoyj
 * @date 2020/8/26 18:45
 */
public class StringDemoTest {

	public static void main(String[] args) {
		printDistinctCharsWithCount("itwanger");
		printDistinctCharsWithCount("chenmowanger");
		printDistinctCharsWithCountMerge("chenmowanger");
	}

	/**
	 * 在字符串中获取不同的字符及其数量
	 *
	 * @param input
	 */
	private static void printDistinctCharsWithCount(String input) {
		Map<Character, Integer> charsWithCountMap = new LinkedHashMap<>();

		for (char c : input.toCharArray()) {
			Integer oldValue = charsWithCountMap.get(c);

			int newValue = (oldValue == null) ? 1 :
				Integer.sum(oldValue, 1);

			charsWithCountMap.put(c, newValue);
		}
		System.out.println(charsWithCountMap);
	}

	/**
	 * 在字符串中获取不同的字符及其数量
	 *
	 * @param input
	 */
	private static void printDistinctCharsWithCountMerge(String input) {
		Map<Character, Integer> charsWithCountMap = new LinkedHashMap<>();

		for (char c : input.toCharArray()) {
			charsWithCountMap.merge(c, 1, Integer::sum);
		}
		System.out.println(charsWithCountMap);
	}

	/**
	 * 反转字符串
	 *
	 * @param input
	 */
	private static void reverseInputString(String input) {
		StringBuilder sb = new StringBuilder(input);
		String result = sb.reverse().toString();
		System.out.println(result);
	}

	/**
	 * 判断一个字符串是前后对称
	 *
	 * @param input
	 */
	private static void checkPalindromeString(String input) {
		boolean result = true;
		int length = input.length();
		for (int i = 0; i < length / 2; i++) {
			if (input.charAt(i) != input.charAt(length - i - 1)) {
				result = false;
				break;
			}
		}
		System.out.println(input + " 对称吗？ " + result);
	}

	/**
	 * 删除所有出现的指定字符
	 *
	 * @param input
	 * @param c
	 */
	private static void removeCharFromString(String input, char c) {
		String result = input.replaceAll(String.valueOf(c), "");
		System.out.println(result);
	}

	/**
	 * 统计字符串中单词数
	 *
	 * @param line
	 */
	private static void countNumberOfWords(String line) {
		String trimmedLine = line.trim();
		int count = trimmedLine.isEmpty() ? 0 : trimmedLine.split("\\s+").length;
		System.out.println(count);
	}

	/**
	 * 检查两个字符串中的字符是否相同
	 *
	 * @param s1
	 * @param s2
	 */
	private static void sameCharsStrings(String s1, String s2) {
		Set<Character> set1 = s1.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
		System.out.println(set1);
		Set<Character> set2 = s2.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
		System.out.println(set2);
		System.out.println(set1.equals(set2));
	}

	/**
	 * 在字符串中找出一个不重复的字符
	 *
	 * @param string
	 * @return
	 */
	private static Character printFirstNonRepeatingChar(String string) {
		char[] chars = string.toCharArray();
		List<Character> discardedChars = new ArrayList<>();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (discardedChars.contains(c))
				continue;
			for (int j = i + 1; j < chars.length; j++) {
				if (c == chars[j]) {
					discardedChars.add(c);
					break;
				} else if (j == chars.length - 1) {
					return c;
				}
			}
		}
		return null;
	}

	/**
	 * 检查字符串中是否包含数字
	 *
	 * @param string
	 */
	private static void digitsOnlyString(String string) {
		if (string.matches("\\d+")) {
			System.out.println("只包含数字的字符串：" + string);
		}
	}


}
