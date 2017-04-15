package web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hibernate.dao.base.Page;

import web.content.Constant;
import web.dto.UserDto;
import web.entity.Goods;
import web.entity.User;
import web.service.IGoodsService;
import web.service.IUserService;
import web.util.MD5Tools;
import web.util.RegexValidateUtil;
import web.util.SessionUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	private final static String JSP_PATH = "user/";

	@Autowired
	@Lazy
	IUserService<User, Serializable> userService;

	@Autowired
	@Lazy
	IGoodsService<Goods, Serializable> goodsService;
     
	@RequiresAuthentication
	@RequestMapping(value = "userLoginOut", method = RequestMethod.GET)
	public String userLoginOut(User user, HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		SessionUtil.setAttribute(request, User.SESSION_USER, null);
		return "ggt/Login/Login";
	}

	@RequiresAuthentication
	@RequestMapping(value = "getPassword", method = RequestMethod.POST)
	public ModelAndView getPassword(UserDto userParam) {
		User user = userService.get(userParam.getId());
		if (!userParam.getNewPassword().equals(userParam.getReNewPassword())) {
			ModelAndView modelAndView = new ModelAndView(getPath("getPassword"));
			logger.error("两次输入的新密码不一至!");
			modelAndView.addObject("msg", "两次输入的新密码不一至");
			modelAndView.addObject("user", user);
			return modelAndView;
		}
		if (userParam.getPasswordanswer().equals(user.getPasswordanswer())) {
			user.setPassword(MD5Tools.MD5(userParam.getNewPassword()));
			userService.update(user);
			if (SecurityUtils.getSubject().getPrincipal() != null) {
				return new ModelAndView("redirect:/user/userIndex");
			}
		} else {
			ModelAndView modelAndView = new ModelAndView(getPath("getPassword"));
			logger.error("答案不正确!");
			modelAndView.addObject("msg", "答案不正确");
			modelAndView.addObject("user", user);
			return modelAndView;
		}
		return new ModelAndView("redirect:/user/toLogin");
	}

	@RequiresAuthentication
	@RequestMapping(value = "owner", method = RequestMethod.GET)
	public ModelAndView owner(String _SCH_name, Integer exchangeGroupId,
			@RequestParam(required = false) Integer pageIndex, HttpServletRequest request) {

		User user = (User) SessionUtil.getAttribute(request, User.SESSION_USER);
		Page page = goodsService.getList(Page.defaultStartIndex, Page.defaultPageSize, user.getId(), _SCH_name,
				exchangeGroupId, null);

		ModelAndView mav = new ModelAndView(getPath("owner"));
		mav.getModelMap().put("goodsList", page.getList());
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		return mav;
	}

	@RequiresAuthentication
	@RequestMapping(value = "toUpdate", method = RequestMethod.GET)
	public ModelAndView toUpdate(HttpServletRequest request) {
		User user = (User) SessionUtil.getAttribute(request, User.SESSION_USER);
		ModelAndView modelAndView = new ModelAndView("user/updateUser");
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@RequiresAuthentication
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public ModelAndView user(User user, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("user/updateUser");
		if (StringUtils.isBlank(user.getUsername())) {
			modelAndView.addObject("msg", "用户名不能为空");
			return modelAndView;
		}
		if (StringUtils.isBlank(user.getPassword())) {
			modelAndView.addObject("msg", "密码不能为空");
			return modelAndView;
		}
		if (!user.getPassword().equals(user.getRepassword())) {
			modelAndView.addObject("msg", "两次输入密码不一至");
			return modelAndView;
		}
		if (StringUtils.isBlank(user.getEmail()) || !RegexValidateUtil.checkEmail(user.getEmail())) {
			modelAndView.addObject("msg", "邮箱为空或者格式不正确");
			return modelAndView;
		}
		if (StringUtils.isBlank(user.getPhone()) || !RegexValidateUtil.checkMobileNumber(user.getPhone())) {
			modelAndView.addObject("msg", "电话为空或者格式不正确");
			return modelAndView;
		}
		if (StringUtils.isBlank(user.getSno())) {
			modelAndView.addObject("msg", "学号不能为空");
			return modelAndView;
		}
		if (StringUtils.isBlank(user.getSname())) {
			modelAndView.addObject("msg", "姓名不能为空");
			return modelAndView;
		}
		if (StringUtils.isBlank(user.getPasswordask())) {
			modelAndView.addObject("msg", "密码找回问题不能为空");
			return modelAndView;
		}
		if (StringUtils.isBlank(user.getPasswordanswer())) {
			modelAndView.addObject("msg", "密码找回答案不能为空");
			return modelAndView;
		}

		user.setPassword(MD5Tools.MD5(user.getPassword()));
		user.setState(Constant.State.STATE_NORMAL);

		userService.update(user);
		SessionUtil.setAttribute(request, User.SESSION_USER, user);
		return new ModelAndView("redirect:/user/owner");
	}

	private String getPath(String path) {
		return JSP_PATH + path;
	}

}
