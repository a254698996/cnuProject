package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.Role;
import web.service.IRoleService;

public class RoleService extends ServiceImpl<Role, Serializable> implements IRoleService<Role, Serializable> {

	public RoleService(Class<Role> entityClass, HibernateEntityDao<Role, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}
}