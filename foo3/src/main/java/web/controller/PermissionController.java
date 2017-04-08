package web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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

		hql = "select p, rp from Permission p ,RolePermission rp";
		Page pagedQuery = rolePermissionService.pagedQuery(hql, pageIndex, Integer.MAX_VALUE);
		Set<RolePermission> rpSet = new HashSet<RolePermission>();
		Set<Permission> pAllSet = new HashSet<Permission>();

		if (pagedQuery.getList() != null && !pagedQuery.getList().isEmpty()) {
			List<Object> list2 = pagedQuery.getList();
			for (Object object : list2) {
				Object[] objArr = (Object[]) object;
				Permission permission = (Permission) objArr[0];
				RolePermission rp = (RolePermission) objArr[1];
				rpSet.add(rp);
				pAllSet.add(permission);
			}
		}

		ModelAndView mav = new ModelAndView(getPath("roleList"));
		mav.getModelMap().put("roleList", page.getList());
		mav.getModelMap().put("rpSet", rpSet);
		mav.getModelMap().put("pAllSet", pAllSet);
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		return mav;
	}

	@RequestMapping(value = "updatePermission/{roleId}", method = RequestMethod.POST)
	public ResponseEntity<Integer> updatePermission(@PathVariable String roleId, String permissions) {
		List<RolePermission> oldRolePermission = rolePermissionService.findBy("roleId", roleId);
		rolePermissionService.removeAll(oldRolePermission);
		if (StringUtils.isNotBlank(permissions)) {
			String[] split = permissions.split(",");
			for (String permission : split) {
				RolePermission rolePermission = new RolePermission();
				rolePermission.setRoleId(roleId);
				rolePermission.setPermissionId(permission);
				rolePermissionService.save(rolePermission);
			}
		}
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}

	@RequestMapping(value = "toAddRole", method = RequestMethod.GET)
	public String toAddRole() {
		return getPath("addRole");
	}

	@RequestMapping(value = "addRole", method = RequestMethod.POST)
	public ModelAndView addRole(Role role, HttpServletRequest request) {
		roleService.save(role);
		return new ModelAndView("redirect:/permission/roleList");
	}
	
	@RequestMapping(value = "toAddPermission", method = RequestMethod.GET)
	public String toAddPermission() {
		return getPath("addPermission");
	}

	@RequestMapping(value = "addPermission", method = RequestMethod.POST)
	public ModelAndView addPermission(Permission permission, HttpServletRequest request) {
		permissionService.save(permission);
		return new ModelAndView("redirect:/permission/roleList");
	}
	
	private String getPath(String path) {
		return JSP_PATH + path;
	}

}
