package web.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hibernate.dao.base.Page;

import util.JSONUtils;
import web.content.Constant;
import web.content.Constant.UserType;
import web.dto.UserDto;
import web.entity.ExchangeOrder;
import web.entity.Goods;
import web.entity.GoodsCategory;
import web.entity.GoodsPic;
import web.entity.NoticeActivity;
import web.entity.Role;
import web.entity.User;
import web.entity.UserRole;
import web.service.IExchangeOrderService;
import web.service.IGoodsCategoryService;
import web.service.IGoodsPicService;
import web.service.IGoodsService;
import web.service.INoticeActivityService;
import web.service.IRoleService;
import web.service.IUserRoleService;
import web.service.IUserService;
import web.util.DateUtil;
import web.util.MD5Tools;
import web.util.RegexValidateUtil;
import web.util.SessionUtil;

@Controller
@RequestMapping("/index")
public class IndexController {

	Logger logger = LoggerFactory.getLogger(IndexController.class);
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

	@Autowired
	@Lazy
	IGoodsService<Goods, Serializable> goodsService;

	@Autowired
	@Lazy
	IGoodsPicService<GoodsPic, Serializable> goodsPicService;
	
	@Autowired
	@Lazy
	IExchangeOrderService<ExchangeOrder, Serializable> exchangeOrderService;

	@RequestMapping(value = "toLogin", method = RequestMethod.GET)
	public String toLogin() {
		return "ggt/Login/Login";
	}

	@RequestMapping(value = "indexNotice/{id}", method = RequestMethod.GET)
	public ModelAndView indexNotice(@PathVariable Integer id) {
		NoticeActivity noticeActivity = noticeActivityService.get(id);
		ModelAndView modelAndView = new ModelAndView("ggt/index_notice");
		modelAndView.addObject("notice", noticeActivity);
		return modelAndView;
	}

	@RequestMapping(value = "userIndex", method = RequestMethod.GET)
	public String userIndex() {
		return "ggt/index";
	}

	@RequestMapping(value = "indexGoodsDetail/{id}", method = RequestMethod.GET)
	public ModelAndView indexGoodsDetail(@PathVariable String id,@RequestParam(required = false) Integer pageIndex) {
		Goods goods = goodsService.getGoodsById(id);
		ModelAndView mav = new ModelAndView("ggt/index_goods_detail");
		mav.addObject("goods", goods);
		pageIndex = pageIndex == null ? Page.defaultStartIndex : pageIndex;
		Page page = exchangeOrderService.pagedQuery("select g,c ,subC,eo ,u from User u, ExchangeOrder  eo,Goods g ,GoodsCategory c, GoodsCategory subC where g.goodsCategoryCode=c.code and g.goodsCategorySubCode=subC.code  and eo.exchangeGoodsId=g.id and eo.exchangeUserId=u.id and eo.goodsId=? ", pageIndex, Page.defaultPageSize,new Object[]{id});
		List<Object> goodsList = new ArrayList<>();
		if (page.getList() != null && !page.getList().isEmpty()) {
			for (Object obj : page.getList()) {
				Object[] objs = (Object[]) obj;
				Goods exchangeGoods = (web.entity.Goods) objs[0];
				GoodsCategory c = (web.entity.GoodsCategory) objs[1];
				GoodsCategory subC = (web.entity.GoodsCategory) objs[2];
				ExchangeOrder eo = (web.entity.ExchangeOrder) objs[3];
				User user = (web.entity.User) objs[4];
				exchangeGoods.setGoodsCategoryName(c.getName());
				exchangeGoods.setGoodsCategorySubName(subC.getName());
				exchangeGoods.setEo(eo);
				eo.setUser(user);
				goodsList.add(exchangeGoods);
			}
		}
		mav.getModelMap().put("goodsList", goodsList);
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		return mav;
	}

	@RequestMapping(value = "indexList/{id}", method = RequestMethod.GET)
	public ModelAndView indexList(@PathVariable Integer id, Goods Goods, String _SCH_name,
			@RequestParam(required = false) Integer pageIndex, HttpServletRequest request) {
		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}

		StringBuffer hql = new StringBuffer(
				"select g ,c ,subC from Goods g, GoodsCategory c, GoodsCategory subC where g.goodsCategoryCode=c.code and g.goodsCategorySubCode=subC.code   ");
		Page page = null;

		List<Object> paramList = new ArrayList<Object>();

		if (StringUtils.isNotBlank(_SCH_name)) {
			hql.append(" and  g.name like ?");
			paramList.add("%" + _SCH_name + "%");
		}

		if (id != null) {
			hql.append(" and  c.id = ?");
			paramList.add(id);
		}

		page = goodsService.pagedQuery(hql.toString(), pageIndex, Page.defaultPageSize, paramList.toArray());

		List<Object> list = page.getList();
		List<Object> goodsList = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			for (Object object : list) {
				Object[] objs = (Object[]) object;
				Goods goods = (web.entity.Goods) objs[0];
				GoodsCategory c = (web.entity.GoodsCategory) objs[1];
				GoodsCategory subC = (web.entity.GoodsCategory) objs[2];
				goods.setGoodsCategoryName(c.getName());
				goods.setGoodsCategorySubName(subC.getName());

				try {
					if (goods.getSendDate() != null) {
						goods.setDaysBetween(DateUtil.daysBetween(goods.getSendDate(), new Date()));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				goodsList.add(goods);
			}
		}
		page.setList(goodsList);

		ModelAndView mav = new ModelAndView("ggt/index_list");
		mav.getModelMap().put("goodsList", page.getList());
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		mav.getModel().put("categoryId", id);
		return mav;
	}

	@RequestMapping(value = "searchList", method = RequestMethod.GET)
	public ModelAndView searchList(String _SCH_name, @RequestParam(required = false) Integer pageIndex,
			HttpServletRequest request) {
		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}

		StringBuffer hql = new StringBuffer(
				"select g ,c ,subC from Goods g, GoodsCategory c, GoodsCategory subC where g.goodsCategoryCode=c.code and g.goodsCategorySubCode=subC.code   ");
		Page page = null;

		List<Object> paramList = new ArrayList<Object>();

		if (StringUtils.isNotBlank(_SCH_name)) {
			hql.append(" and  g.name like ?");
			paramList.add("%" + _SCH_name + "%");
		}

		page = goodsService.pagedQuery(hql.toString(), pageIndex, Page.defaultPageSize, paramList.toArray());

		List<Object> list = page.getList();
		List<Object> goodsList = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			for (Object object : list) {
				Object[] objs = (Object[]) object;
				Goods goods = (web.entity.Goods) objs[0];
				GoodsCategory c = (web.entity.GoodsCategory) objs[1];
				GoodsCategory subC = (web.entity.GoodsCategory) objs[2];
				goods.setGoodsCategoryName(c.getName());
				goods.setGoodsCategorySubName(subC.getName());

				try {
					if (goods.getSendDate() != null) {
						goods.setDaysBetween(DateUtil.daysBetween(goods.getSendDate(), new Date()));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				goodsList.add(goods);
			}
		}
		page.setList(goodsList);

		ModelAndView mav = new ModelAndView("ggt/index_list_search");
		mav.getModelMap().put("goodsList", page.getList());
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		return mav;
	}

	@RequestMapping(value = "userLogin", method = RequestMethod.POST)
	public ModelAndView userLogin(User user, HttpServletRequest request) {
		try {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),
					MD5Tools.MD5(user.getPassword()));
			SecurityUtils.getSubject().login(usernamePasswordToken);
			User userAllInfo = userService.getUserAllInfo(user);
			SessionUtil.setAttribute(request, User.SESSION_USER, userAllInfo);
			System.out.println(JSONUtils.obj2json(userAllInfo));
			Object attribute =  SessionUtil.getAttribute(request, User.SESSION_USER);
			System.out.println(JSONUtils.obj2json(attribute));
		} catch (AuthenticationException e) {
			e.printStackTrace();

			ModelAndView modelAndView = new ModelAndView("ggt/Login/Login");
			if (e instanceof LockedAccountException) {
				modelAndView.addObject("msg", "该用被锁定");
			} else if (e instanceof UnknownAccountException) {
				modelAndView.addObject("msg", "用户名或密码错误");
			} else {
				modelAndView.addObject("msg", "未知错误");
			}
			return modelAndView;
		}
		// return new ModelAndView("redirect:/admin/userList");
		if (user.getUserType().equals(UserType.ADMIN)) {
			return new ModelAndView("redirect:/admin/adminIndex");
		}
		return new ModelAndView("redirect:/index/userIndex");
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
		ModelAndView modelAndView = new ModelAndView(getPath("reg"));
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

		boolean unique = userService.isUnique(user, "username");
		if (unique) {
			user.setPassword(MD5Tools.MD5(user.getPassword()));
			user.setState(Constant.State.STATE_NORMAL);
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
				return new ModelAndView("redirect:/index/userIndex");
			}
		} else {
			ModelAndView modelAndView = new ModelAndView(getPath("getPassword"));
			logger.error("答案不正确!");
			modelAndView.addObject("msg", "答案不正确");
			modelAndView.addObject("user", user);
			return modelAndView;
		}
		// return new ModelAndView(getPath("userLogin"));
		return new ModelAndView("redirect:/index/toLogin");
	}

	private String getPath(String path) {
		return JSP_PATH + path;
	}

}
