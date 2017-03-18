package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath*:/conf/context/applicationContext-AppConfig.xml",
		"classpath*:/conf/context/applicationContext-DaoConfig.xml",
		"classpath*:/conf/context/applicationContext-DataSourceConfig.xml",
		"classpath*:/conf/context/applicationContext-MvcConfig.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BasicNoRellbackTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void test() {
		Assert.assertEquals(1, 1);
	}
}
