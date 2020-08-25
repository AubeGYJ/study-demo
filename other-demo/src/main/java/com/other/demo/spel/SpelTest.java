package com.other.demo.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author guoyj
 * @date 2020/8/24 17:59
 */
public class SpelTest {

	public static void main(String[] args) {
		//初始化对象
		Account account = new Account("Deniro");
		account.setFootballCount(10);
		account.addFriend(new Friend("Jack"));

		//解析器
		ExpressionParser parser = new SpelExpressionParser();
		//解析上下文
		EvaluationContext context = new StandardEvaluationContext(account);

		//获取不同类型的属性
		String name = (String) parser.parseExpression("name").getValue(context);
		System.out.println(name);
		int count = (Integer) parser.parseExpression("footballCount+1").getValue(context);
		System.out.println(count);

		//获取嵌套类中的属性
		String friend = (String) parser.parseExpression("friend.name").getValue(context);
		System.out.println(friend);
	}
}
