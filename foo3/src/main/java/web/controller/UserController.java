package web.controller;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
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

	@Autowired
	@Lazy
	IUserService<User, Serializable> userService;

	@RequestMapping(value = "toLogin", method = RequestMethod.GET)
	public String toLogin() {
		return "user/userLogin";
	}

	@RequestMapping(value = "userLogin", method = RequestMethod.POST)
	public ModelAndView userLogin(User user) {
//		Example create = Example.create(user);
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);  
//		detachedCriteria.createAlias("enumConstByFlagIsvalid", 
		
//		userService.pagedQuery(1, 1, create);

		return new ModelAndView("user/index");
	}

	@RequestMapping(value = "toReg", method = RequestMethod.GET)
	public String toreg() {
		return "user/reg";
	}

	@RequestMapping(value = "reg", method = RequestMethod.POST)
	public ModelAndView reg(User user) {
		boolean unique = userService.isUnique(user, "username");
		if (unique) {
			userService.save(user);
		} else {
			logger.info("用户名称重复");
		}
		return new ModelAndView("user/regSuccess");
	}

	@RequestMapping(value = "userList", method = RequestMethod.GET)
	public ModelAndView userList(User user) {
		Criteria criteria = userService.getCriteria();
		Page pagedQuery = userService.pagedQuery(criteria, Page.defaultStartIndex, Page.defaultPageSize);
		List list = pagedQuery.getList();
		logger.info("list.size " + list.size());
		ModelAndView modelAndView = new ModelAndView("user/userList");
		modelAndView.getModelMap().put("userList", list);
		return modelAndView;
	}

}
