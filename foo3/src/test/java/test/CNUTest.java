package test;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import web.entity.City;
import web.service.ICityService;

public class CNUTest extends BasicNoRellbackTest {

	// @Autowired
	// @Lazy
	// IUserService<User, Serializable> userService;

	@Autowired
	@Lazy
	ICityService<City, Serializable> cityService;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");

	// @Test
	// public void userSavetest() {
	// User user = new User();
	// user.setEmail("tarzan@126.com");
	// user.setMemo("哈哈");
	// user.setPassword("123456");
	// user.setPasswordask("你的宠物名字");
	// user.setPhone("13970733521");
	// user.setSname("张三");
	// user.setSno("99884");
	// userService.save(user);
	//
	// User user2 = userService.get(1);
	// Assert.assertEquals(user2.getEmail(), "tarzan@126.com");
	// Assert.assertEquals(1, 1);
	// }

	@Test
	public void testCIty() {
		City city = new City();
		city.setId(99);
		city.setName("哈哈哈");
		city.setPassword("2123212133");

		cityService.save(city);
		City city2 = cityService.get(99);
		Assert.assertEquals(city2.getName(), "哈哈哈");
	}
}
