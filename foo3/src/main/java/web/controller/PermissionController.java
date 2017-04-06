package web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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

import web.entity.Permission;
import web.entity.Role;
import web.entity.RolePermission;
import web.entity.User;
import web.service.IPermissionService;
import web.service.IRolePermissionService;
import web.service.IRoleService;
import web.service.IUserService;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	Logger logger = LoggerFactory.getLogger(PermissionController.class);
	private final static String JSP_PATH = "admin/";

	@Autowired
	@Lazy
	IUserService<User, Serializable> userService;

	@Autowired
	@Lazy
	IRoleService<Role, Serializable> roleService;

	@Autowired
	@Lazy
	IPermissionService<Permission, Serializable> permissionService;

	@Autowired
	@Lazy
	IRolePermissionService<RolePermission, Serializable> rolePermissionService;

	@RequestMapping(value = "roleList", method = RequestMethod.GET)
	public ModelAndView roleList(Role role, @RequestParam(required = false) Integer pageIndex) {
		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}

		String hql = "from Role";

		List<Object> list = new ArrayList<Object>();

		Page page = userService.pagedQuery(hql, pageIndex, Page.defaultPageSize, list.toArray());

		hql = "select p, rp from Permission p ,RolePermission rp where p.id=rp.permissionId ";
		Page pagedQuery = rolePermissionService.pagedQuery(hql, pageIndex, Page.defaultPageSize);
		Set<Permission> pSet = new HashSet<Permission>();
		if (pagedQuery.getList() != null && !pagedQuery.getList().isEmpty()) {
			List<Object> list2 = pagedQuery.getList();
			for (Object object : list2) {
				Object[] objArr = (Object[]) object;
				Permission p = (Permission) objArr[0];
				RolePermission rp = (RolePermission) objArr[1];
				p.setRoleId(rp.getRoleId());
				pSet.add(p);
			}
		}

		ModelAndView mav = new ModelAndView(getPath("roleList"));
		mav.getModelMap().put("roleList", page.getList());
		mav.getModelMap().put("pSet", pSet);
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		return mav;
	}

	@RequestMapping(value = "toAddRole", method = RequestMethod.GET)
	public String toAddRole() {
		return getPath("addRole");
	}

	@RequestMapping(value = "addRole", method = RequestMethod.POST)
	public ModelAndView addRole(Role role, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(getPath("userLogin"));
		modelAndView.addObject("msg", "用户名或密码错误");
		return modelAndView;
	}

	private String getPath(String path) {
		return JSP_PATH + path;
	}

}
