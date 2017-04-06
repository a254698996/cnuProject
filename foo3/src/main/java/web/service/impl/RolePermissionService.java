package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.RolePermission;
import web.service.IRolePermissionService;

public class RolePermissionService extends ServiceImpl<RolePermission, Serializable>
		implements IRolePermissionService<RolePermission, Serializable> {

	public RolePermissionService(Class<RolePermission> entityClass,
			HibernateEntityDao<RolePermission, Serializable> hedao, HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}
}