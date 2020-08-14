package com.other.demo.beanconvert;

import freemarker.template.utility.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

/**
 * Spring BeanUtils
 * Apache BeanUtils
 * Dozer
 * Orika
 * MapStruct
 * ModelMapper
 * JMapper
 * <p>
 * MapStruct 是用于生成类型安全的 Bean 映射类的 Java 注解处理器。你所要做的就是定义一个映射器接口，声明任何需要映射的方法。
 * 在编译过程中，MapStruct 将生成该接口的实现。此实现使用纯 Java 的方法调用源对象和目标对象之间进行映射，并非 Java 反射机制。
 * 与手工编写映射代码相比，MapStruct 通过生成冗长且容易出错的代码来节省时间。
 * 在配置方法的约定之后，MapStruct 使用了合理的默认值，但在配置或实现特殊行为时将不再适用。
 * <p>
 * 与动态映射框架相比，MapStruct 具有以下优点：
 * 使用纯 Java 方法代替 Java 反射机制快速执行。
 * 编译时类型安全：只能映射彼此的对象和属性，不能映射一个 Order 实体到一个 Customer DTO 中等等。
 * 如果无法映射实体或属性，则在编译时清除错误报告。
 * <p>
 * https://blog.csdn.net/zhige_me/article/details/80699784
 *
 * @author guoyj
 * @date 2020/8/11 15:20
 */
@Mapper
public interface ConvertUtil {

	ConvertUtil INSTANCE = Mappers.getMapper(ConvertUtil.class);

	@Mappings({
		@Mapping(target = "roleIds", ignore = true)
	})
	TestBO convert(TestDO testDO);

	@Mappings({
		@Mapping(source = "id", target = "userId"),
		@Mapping(source = "birthday", target = "birthDateFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
		@Mapping(target = "birthExpressionFormat", expression = "java(cn.hutool.core.date.DateUtil.format(testBO.getBirthday(),\"yyyy-MM-dd HH:mm:ss\"))"),
		@Mapping(source = "test.age", target = "age"),
		@Mapping(target = "password", ignore = true)
	})
	TestVO convert(TestBO testBO);

	@Mappings({
		@Mapping(source = "testDO.id", target = "userId"),
		@Mapping(source = "testBO.username", target = "username"),
		@Mapping(source = "testBO.birthday", target = "birthDateFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
		@Mapping(target = "birthExpressionFormat", expression = "java(cn.hutool.core.date.DateUtil.format(testBO.getBirthday(),\"yyyy-MM-dd HH:mm:ss\"))"),
		@Mapping(source = "testBO.test.age", target = "age"),
		@Mapping(target = "password", ignore = true)
	})
	TestVO convert(TestDO testDO, TestBO testBO);

	@Mappings({
		@Mapping(source = "roleIds", target = "roleIds", qualifiedByName = "id2ids"),
	})
	TestBO convert2(TestDO testDO);

	default List<String> id2ids(String roleIds) {
		System.out.println(roleIds);
		System.out.println(StringUtils.split(roleIds, ","));
		return Arrays.asList(StringUtils.split(roleIds, ","));
	}
}
