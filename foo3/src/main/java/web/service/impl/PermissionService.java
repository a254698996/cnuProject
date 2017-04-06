package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.Permission;
import web.service.IPermissionService;

public class PermissionService extends ServiceImpl<Permission, Serializable>
		implements IPermissionService<Permission, Serializable> {

	public PermissionService(Class<Permission> entityClass, HibernateEntityDao<Permission, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}
}