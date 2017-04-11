package web.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
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

import com.hibernate.dao.base.Page;

import web.content.Constant;
import web.content.Constant.UserType;
import web.dto.UserDto;
import web.entity.GoodsCategory;
import web.entity.NoticeActivity;
import web.entity.Role;
import web.entity.User;
import web.entity.UserRole;
import web.service.IGoodsCategoryService;
import web.service.INoticeActivityService;
import web.service.IRoleService;
import web.service.IUserRoleService;
import web.service.IUserService;
import web.util.MD5Tools;

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
	IRoleService<Role, Serializable> roleService;

	@Autowired
	@Lazy
	IUserRoleService<UserRole, Serializable> userRoleService;

	@Autowired
	@Lazy
	IGoodsCategoryService<GoodsCategory, Serializable> goodsCategoryService;

	@Autowired
	@Lazy
	INoticeActivityService<NoticeActivity, Serializable> noticeActivityService;

	@RequestMapping(value = "toLogin", method = RequestMethod.GET)
	public String toLogin() {
		return "ggt/Login/Login";
	}

	@RequestMapping(value = "userIndex", method = RequestMethod.GET)
	public ModelAndView userIndex() {
		ModelAndView modelAndView = new ModelAndView("ggt/index");

		Page goodsCategoryPage =goodsCategoryService.pagedQuery("from GoodsCategory g where g.pcode is null ", Page.defaultStartIndex, 7);
		modelAndView.addObject("goodsCategoryList", goodsCategoryPage.getList());

		Page pagedQuery = noticeActivityService.pagedQuery(
				"from NoticeActivity n where n.type=1 and n.state=? and n.endDate >= ? and n.sendDate <=? ", Page.defaultStartIndex, 3,
				new Object[]{ Constant.State.STATE_NORMAL, new Date(),new Date()});
		modelAndView.addObject("noticeList", pagedQuery.getList());
		  pagedQuery = noticeActivityService.pagedQuery(
				"from NoticeActivity n where n.type=2 and n.state=? and n.endDate >= ? and n.sendDate <=? ", Page.defaultStartIndex, 4,
				new Object[]{ Constant.State.STATE_NORMAL, new Date(),new Date()});
		modelAndView.addObject("activityList", pagedQuery.getList());

		// pagedQuery = noticeActivityService.pagedQuery("from NoticeActivity n
		// where n.type=2 and state=1 ", Page.defaultStartIndex, 3 );
		// modelAndView.addObject("activityList", pagedQuery.getList());

		return modelAndView;
	}

	@RequestMapping(value = "userLogin", method = RequestMethod.POST)
	public ModelAndView userLogin(User user, HttpServletRequest request) {
		// User returnUser = userService.getUserAllInfo(user);
		// if (returnUser != null) {
		// SessionUtil.setAttribute(request, User.SESSION_USER, returnUser);
		try {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),
					MD5Tools.MD5(user.getPassword()));
			SecurityUtils.getSubject().login(usernamePasswordToken);
		} catch (AuthenticationException e) {
			e.printStackTrace();

			ModelAndView modelAndView = new ModelAndView("ggt/Login/Login");
			if (e instanceof LockedAccountException) {
				modelAndView.addObject("msg", "该用被锁定");
			}else if(e instanceof UnknownAccountException){
				modelAndView.addObject("msg", "用户名或密码错误");
			}else {
				modelAndView.addObject("msg", "未知错误");
			}
			return modelAndView;
		}
		// return new ModelAndView("redirect:/admin/userList");
		if (user.getUserType().equals(UserType.ADMIN)) {
			return new ModelAndView("redirect:/admin/adminIndex");
		}
		return new ModelAndView("redirect:/user/userIndex");
	}

	@RequestMapping(value = "userLoginOut", method = RequestMethod.GET)
	public String userLoginOut(User user, HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		return "ggt/Login/Login";
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
			user.setPassword(MD5Tools.MD5(user.getPassword()));
			userService.save(user);

			List<Role> roleList = roleService.findBy("name", "user");
			if (roleList != null && !roleList.isEmpty()) {
				Role role = roleList.get(0);
				UserRole userRole = new UserRole();
				userRole.setRoleId(String.valueOf(role.getId()));
				userRole.setUserId(String.valueOf(user.getId()));
				userRoleService.save(userRole);
			}
		} else {
			ModelAndView modelAndView = new ModelAndView(getPath("reg"));
			modelAndView.addObject("msg", "用户名称重复");
			return modelAndView;
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
		// return new ModelAndView(getPath("userLogin"));
		return new ModelAndView("redirect:/user/toLogin");
	}

	private String getPath(String path) {
		return JSP_PATH + path;
	}

}
