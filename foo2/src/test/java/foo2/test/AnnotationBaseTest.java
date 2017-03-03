//package foo2.test;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import foo2.conf.AppConfig;
//import foo2.web.function.service.CityService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
//@WebAppConfiguration
//public class AnnotationBaseTest {
//
//	@Autowired
//	CityService cityService;
//
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
//	public void findByUserId() {
//		System.out.println(cityService.getCityName());
//		System.out.println(cityService.findAll().size());
//	}
//	
////	@Test
//	public void getAllCategoryTest22() throws Exception {
//		ResultActions andExpect = mockMvc.perform(get("/demo/test").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
//				.andExpect(content().contentType(MediaType.TEXT_HTML));
//	}
//
//	public void getAllCategoryTest() throws Exception {
//		mockMvc.perform(get("/demo/test").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
//				.andExpect(content().json("{'foo':'bar'}"));
//	}
//
//}
