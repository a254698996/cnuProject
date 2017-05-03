package web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hibernate.dao.base.Page;

import web.content.Constant;
import web.entity.Goods;
import web.entity.GoodsCategory;
import web.entity.GoodsPic;
import web.entity.Menu;
import web.entity.Role;
import web.entity.User;
import web.entity.UserRole;
import web.service.IGoodsCategoryService;
import web.service.IGoodsPicService;
import web.service.IGoodsService;
import web.service.IMenuService;
import web.service.IRoleService;
import web.service.IUserRoleService;
import web.service.IUserService;
import web.util.SessionUtil;

@Controller
@RequiresRoles(value = { "admin", "superadmin" }, logical = Logical.OR)
@RequestMapping("/admin")
public class AdminController {

	Logger logger = LoggerFactory.getLogger(AdminController.class);
	private final static String JSP_PATH = "admin/";

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
	IMenuService<Menu, Serializable> menuService;

	@Autowired
	@Lazy
	IGoodsService<Goods, Serializable> goodsService;

	@Autowired
	@Lazy
	IGoodsPicService<GoodsPic, Serializable> goodsPicService;

	@Autowired
	@Lazy
	IGoodsCategoryService<GoodsCategory, Serializable> goodsCategoryService;

	@RequestMapping(value = "adminIndex", method = RequestMethod.GET)
	public ModelAndView adminIndex(HttpServletRequest request) {
		List menuList = menuService.find("from Menu");
		ModelAndView modelAndView = new ModelAndView(getPath("adminIndex"));
		User currUser = (User) SessionUtil.getAttribute(request, User.SESSION_USER);

		List list = new ArrayList();
		Set<String> permissionSet = currUser.getPermissionSet();
		if (permissionSet != null && !permissionSet.isEmpty()) {
			for (Object obj : menuList) {
				Menu m = (Menu) obj;
				for (String permission : permissionSet) {
					if (m.getName().equals(permission)) {
						list.add(m);
						break;
					}
				}
			}
		}

		modelAndView.addObject("menuList", list);
		modelAndView.addObject("indexList", list.get(0));
		return modelAndView;
	}

	@RequestMapping(value = "userList", method = RequestMethod.GET)
	public ModelAndView userList(User user, @RequestParam(required = false) Integer pageIndex) {
		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}
		String hql = "from User u where 1=1  ";

		List<Object> list = new ArrayList<Object>();

		if (StringUtils.isNotBlank(user.getSno())) {
			hql += "and u.sno like ? ";
			list.add("%" + user.getSno() + "%");
		}

		if (StringUtils.isNotBlank(user.getSname())) {
			hql += "and u.sname like ? ";
			list.add("%" + user.getSname() + "%");
		}

		if (StringUtils.isNotBlank(user.getUsername())) {
			hql += "and u.username like ? ";
			list.add("%" + user.getUsername() + "%");
		}

		Page page = userService.pagedQuery(hql, pageIndex, Page.defaultPageSize, list.toArray());

		ModelAndView mav = new ModelAndView(getPath("userList"));
		mav.getModelMap().put("userList", page.getList());
		mav.getModelMap().put("roleList", getRoleList());
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		return mav;
	}

	@SuppressWarnings("unchecked")
	private List<Role> getRoleList() {
		return roleService.find("from Role ");
	}

	@RequestMapping(value = "updateRole/{id}", method = RequestMethod.POST)
	public ResponseEntity<Integer> updateRole(@PathVariable String id, String roleIds) {
		List<UserRole> userRoleList = userRoleService.findBy("userId", id);
		userRoleService.removeAll(userRoleList);
		if (StringUtils.isNotBlank(roleIds)) {
			String[] split = roleIds.split(",");
			for (String roleId : split) {
				UserRole userRole = new UserRole();
				userRole.setRoleId(roleId);
				userRole.setUserId(String.valueOf(id));
				userRoleService.save(userRole);
			}
		}
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}

	@RequestMapping(value = "getUserRole/{userId}", method = RequestMethod.GET)
	public ResponseEntity<List<UserRole>> getUserRole(@PathVariable String userId) {
		List<UserRole> userRoleList = userRoleService.findBy("userId", userId);
		return new ResponseEntity<List<UserRole>>(userRoleList, HttpStatus.OK);
	}

	@RequestMapping(value = "changeUserState/{id}", method = RequestMethod.GET)
	public ModelAndView changeUserState(@PathVariable int id) {
		User user = userService.get(id);
		if (user.getState() == Constant.State.STATE_NORMAL) {
			user.setState(Constant.State.STATE_NOT_NORMAL);
		} else {
			user.setState(Constant.State.STATE_NORMAL);
		}
		userService.update(user);
		return new ModelAndView("redirect:/admin/userList");
	}

	private String getPath(String path) {
		return JSP_PATH + path;
	}

}
