package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.UserRole;
import web.service.IUserRoleService;

public class UserRoleService extends ServiceImpl<UserRole, Serializable>
		implements IUserRoleService<UserRole, Serializable> {

	public UserRoleService(Class<UserRole> entityClass, HibernateEntityDao<UserRole, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}
}