package test;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import web.entity.Menu;
import web.entity.User;
import web.service.IMenuService;
import web.service.IUserService;

public class CNUTest extends BasicNoRellbackTest {

	@Autowired
	@Lazy
	IUserService<User, Serializable> userService;

	@Autowired
	@Lazy
	IMenuService<Menu, Serializable> menuService;

//	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");

	@Test
	public void menuTest() {
		Menu menu = new Menu();
		menu.setName("测试菜单");
		menu.setState(Menu.STATE_NORMAL);
		menu.setUrl("www.baidu.com");
		menuService.save(menu);

		int id = menu.getId();
		Menu menu2 = menuService.get(id);
		Assert.assertEquals(menu2.getName(), menu.getName());
		Assert.assertEquals(menu2.getUrl(), menu.getUrl());
		Assert.assertEquals(menu2.getState(), menu.getState());
		menuService.remove(menu2);
	}

 	@Test
	public void userSavetest() {
		User user = new User();
		user.setEmail("tarzan@126.com");
		user.setMemo("哈哈");
		user.setPassword("123456");
		user.setPasswordask("你的宠物名字");
		user.setPhone("13970733521");
		user.setSname("张三2222");
		user.setSno("99884");
		user.setUsername("qweqweqwe");
		userService.save(user);

		User user2 = userService.get(1);
		Assert.assertEquals(user2.getEmail(), "tarzan@126.com");
		Assert.assertEquals(1, 1);
		userService.remove(user2);
	}

}
