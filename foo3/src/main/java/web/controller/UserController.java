package web.controller;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hibernate.dao.base.Page;

import web.entity.User;
import web.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	private final static String JSP_PATH = "user/";

	@Autowired
	@Lazy
	IUserService<User, Serializable> userService;

	@RequestMapping(value = "toLogin", method = RequestMethod.GET)
	public String toLogin() {
		return getPath("userLogin");
	}

	@RequestMapping(value = "userLogin", method = RequestMethod.POST)
	public ModelAndView userLogin(User user) {
		User returnUser = userService.queryBeanByHql(user);
		if (returnUser != null) {
			return new ModelAndView("user/index");
		} else {
			return new ModelAndView(getPath("userLogin"));
		}
	}

	@RequestMapping(value = "toReg", method = RequestMethod.GET)
	public String toreg() {
		return getPath("reg");
	}

	@RequestMapping(value = "reg", method = RequestMethod.POST)
	public ModelAndView reg(User user) {
		boolean unique = userService.isUnique(user, "username");
		if (unique) {
			userService.save(user);
		} else {
			logger.info("用户名称重复");
		}
		return new ModelAndView(getPath("regSuccess"));
	}

	@RequestMapping(value = "userList", method = RequestMethod.GET)
	public ModelAndView userList(User user) {
		Criteria criteria = userService.getCriteria();
		Page pagedQuery = userService.pagedQuery(criteria, Page.defaultStartIndex, Page.defaultPageSize);
		List<Object> list = pagedQuery.getList();
		ModelAndView modelAndView = new ModelAndView(getPath("userList"));
		modelAndView.getModelMap().put("userList", list);
		return modelAndView;
	}

	private String getPath(String path) {
		return JSP_PATH + path;
	}

}
