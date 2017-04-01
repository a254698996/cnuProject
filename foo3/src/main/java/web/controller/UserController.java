package web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import web.dto.UserDto;
import web.entity.User;
import web.service.IUserService;
import web.util.SessionUtil;

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
	public ModelAndView userLogin(User user,HttpServletRequest request) {
		User returnUser = userService.queryBeanByHql(user);
		if (returnUser != null) {
			SessionUtil.setAttribute(request, User.SESSION_USER,returnUser);
			return new ModelAndView("user/index");
		} else {
			return new ModelAndView(getPath("userLogin"));
		}
	}

	@ResponseBody
	@RequestMapping(value = "userExist", method = RequestMethod.POST)
	public String getUserName(User user) {
		User findUniqueBy = userService.findUniqueBy("username", user.getUsername());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("exist", findUniqueBy != null);
		return jsonObject.toString();
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

	@RequestMapping(value = "toGetPassword", method = RequestMethod.GET)
	public ModelAndView toGetPassword(User user) {
		User dataUser = userService.findUniqueBy("username", user.getUsername());
		ModelAndView modelAndView = new ModelAndView(getPath("getPassword"));
		modelAndView.addObject("user", dataUser);
		return modelAndView;
	}

	@RequestMapping(value = "getPassword", method = RequestMethod.POST)
	public String getPassword(UserDto userParam) {
		if (!userParam.getNewPassword().equals(userParam.getReNewPassword())) {
			logger.error("两次输入的新密码不一至!");
			return null;
		}
		User user = userService.get(userParam.getId());
		if (userParam.getPasswordanswer().equals(user.getPasswordanswer())) {
			user.setPassword(userParam.getNewPassword());
			userService.update(user);
		}
		return getPath("getPasswordSuccess");
	}

	private String getPath(String path) {
		return JSP_PATH + path;
	}

}
