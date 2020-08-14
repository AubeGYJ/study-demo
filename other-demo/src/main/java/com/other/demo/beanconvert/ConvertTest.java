package com.other.demo.beanconvert;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author guoyj
 * @date 2020/8/11 15:40
 */
@Slf4j
public class ConvertTest {

	public static void main(String[] args) {
		// 创建 TestDO 对象
		TestDO testDO = new TestDO()
			.setId(1).setUsername("yudaoyuanma").setPassword("buzhidao").setBirthday(new Date());
		// 进行转换
		TestBO testBO = ConvertUtil.INSTANCE.convert(testDO);
		log.error(JSON.toJSONString(testBO));

		TestBO testBO1 = new TestBO()
			.setId(2).setUsername("yudaoyuanmabo").setPassword("buzhidaobo").setBirthday(DateUtil.beginOfMonth(new Date())).setTest(new Test().setAge(3));
		// 进行转换
		TestVO testVO = ConvertUtil.INSTANCE.convert(testBO1);
		log.error(JSON.toJSONString(testVO));

		// 进行转换
		TestVO testVO1 = ConvertUtil.INSTANCE.convert(testDO,testBO1);
		log.error(JSON.toJSONString(testVO1));


		TestDO testDO1 = new TestDO()
			.setId(1).setUsername("yudaoyuanma").setPassword("buzhidao").setBirthday(new Date()).setRoleIds("1,2,3,4");
		// 进行转换
		TestBO testBO2 = ConvertUtil.INSTANCE.convert2(testDO1);
		log.error(JSON.toJSONString(testBO2));
	}
}
