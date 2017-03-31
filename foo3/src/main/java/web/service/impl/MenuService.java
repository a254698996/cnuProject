package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.Menu;
import web.service.IMenuService;

public class MenuService extends ServiceImpl<Menu, Serializable> implements IMenuService<Menu, Serializable> {

	public MenuService(Class<Menu> entityClass, HibernateEntityDao<Menu, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}
}