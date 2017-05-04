package test;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import org.junit.Assert;

@ContextConfiguration(locations = { "classpath*:/conf/context/applicationContext-AppConfig.xml"  })
public class BasicTest extends AbstractTransactionalJUnit4SpringContextTests {
								 
	@Test
	public void test(){
		Assert.assertEquals(2, 2);
	}
}
