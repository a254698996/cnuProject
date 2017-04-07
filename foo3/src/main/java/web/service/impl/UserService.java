package web.service.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.dao.base.Page;
import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.Permission;
import web.entity.Role;
import web.entity.User;
import web.service.IUserService;
import web.util.MD5Tools;

public class UserService extends ServiceImpl<User, Serializable> implements IUserService<User, Serializable> {

	public UserService(Class<User> entityClass, HibernateEntityDao<User, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public User getUserAllInfo(User user) {
		user.setPassword(MD5Tools.MD5(user.getPassword()));
		User returnUser = queryBeanByHql(user);

		if (returnUser != null) {
			String hql = "select p, r from Permission p ,RolePermission rp,Role r,UserRole ur where r.id=rp.roleId and p.id=rp.permissionId and ur.roleId=r.id and ur.userId=? ";
			Page pagedQuery = this.hdao.pagedQuery(hql, Page.defaultStartIndex, Page.defaultPageSize,
					new Object[] { String.valueOf(returnUser.getId()) });
			if (pagedQuery.getList() != null && !pagedQuery.getList().isEmpty()) {
				Set<String> roleSet = new HashSet<String>();
				Set<String> permissionSet = new HashSet<String>();
				for (Object obj : pagedQuery.getList()) {
					Object[] temp = (Object[]) obj;
					Permission p = (Permission) temp[0];
					Role r = (Role) temp[1];
					roleSet.add(r.getName());
					permissionSet.add(p.getName());
				}
				returnUser.setRoleSet(roleSet);
				returnUser.setPermissionSet(permissionSet);
			}
		}
		return returnUser;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public User getUserRolesAndPermissions(String userName) {
		User returnUser = new User();
		String hql = " from Permission p ,Role r, RolePermission rp,UserRole ur ,User u  where r.id=rp.roleId and p.id=rp.permissionId and ur.roleId=r.id and  ur.userId=u.id and u.username=? ";
		Page pagedQuery = this.hdao.pagedQuery(hql, Page.defaultStartIndex, Page.defaultPageSize,
				new Object[] { userName });
		if (pagedQuery.getList() != null && !pagedQuery.getList().isEmpty()) {
			Set<String> roleSet = new HashSet<String>();
			Set<String> permissionSet = new HashSet<String>();
			for (Object obj : pagedQuery.getList()) {
				Object[] temp = (Object[]) obj;
				Permission p = (Permission) temp[0];
				Role r = (Role) temp[1];
				roleSet.add(r.getName());
				permissionSet.add(p.getName());
			}
			returnUser.setRoleSet(roleSet);
			returnUser.setPermissionSet(permissionSet);
		}
		return returnUser;
	}

}