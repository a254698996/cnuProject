//package foo2.test;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath*:**web-config.xml")
//@WebAppConfiguration
//public class MockMvcTest extends AbstractJUnit4SpringContextTests {
//	private MockMvc mockMvc;
//
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	@Before
//	public void setUp() throws Exception {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//	}
//
//	@Test
//	public void hehe() {
//		Assert.assertEquals("1", "1");
//	}
//
//}