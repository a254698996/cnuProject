package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.User;
import web.service.IUserService;

public class UserService extends ServiceImpl<User, Serializable> implements IUserService<User, Serializable> {

	public UserService(Class<User> entityClass, HibernateEntityDao<User, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}
}