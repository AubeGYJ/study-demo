package com.other.demo;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author guoyj
 * @date 2020/7/28 17:44
 */
@SpringBootTest
public class ScrewApplicationTests {
	@Autowired
	ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		DataSource dataSource = applicationContext.getBean(DataSource.class);

		// 1、生成文件配置
		EngineConfig engineConfig = EngineConfig.builder()
			//生成文件路径
			.fileOutputDir("/Users/mac/Desktop")
			//打开目录
			.openOutputDir(false)
			//文件类型
			.fileType(EngineFileType.HTML)
			//生成模板实现
			.produceType(EngineTemplateType.freemarker).build();

		// 忽略表名
		List<String> ignoreTableName = Arrays.asList("aa", "test_group");
		// 忽略表前缀
		List<String> ignorePrefix = Collections.singletonList("czb_");
		// 忽略表后缀
		List<String> ignoreSuffix = Arrays.asList("_test", "_test1");

		// 2、配置想要忽略的表
		ProcessConfig processConfig = ProcessConfig.builder()
			.ignoreTableName(ignoreTableName)
			.ignoreTablePrefix(ignorePrefix)
			.ignoreTableSuffix(ignoreSuffix)
			.build();

		// 3、生成文档配置（包含以下自定义版本号、描述等配置连接）
		Configuration config = Configuration.builder()
			.version("1.0.0")
			.description("数据库设计文档生成")
			.dataSource(dataSource)
			.engineConfig(engineConfig)
			.produceConfig(processConfig).build();

		// 4、执行生成
		new DocumentationExecute(config).execute();
	}
}
