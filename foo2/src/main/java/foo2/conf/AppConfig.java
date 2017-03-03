package foo2.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import foo2.web.function.model.TestBean;

//import web.config.CachingConfig;
//import web.config.DaoConfig;

@Configuration
@ComponentScan(basePackages = "foo2.web.function", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
@EnableAspectJAutoProxy(proxyTargetClass = true)
// @ImportResource({
// "classpath:config/context/applicationContext-hibernate4.xml" })
//@Import({ DaoConfigMysql.class, DubboConfig.class })
@Import({ DaoConfigMysql.class })
@PropertySource("classpath:/conf/properties/app.properties")
public class AppConfig {
	@Autowired
	Environment env;

	static {
		System.out.println("加载  AppConfig.....");
	}

	@Bean
	public TestBean testBean() {
		TestBean testBean = new TestBean();
		testBean.setName(env.getProperty("testbean.name"));
		return testBean;
	}
}
