package web.service.impl;

import java.io.Serializable;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.User;
import web.service.IUserService;

public class UserService extends ServiceImpl<User, Serializable> implements IUserService<User, Serializable> {

	public UserService(Class<User> entityClass, HibernateEntityDao<User, Serializable> hedao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
	}
}