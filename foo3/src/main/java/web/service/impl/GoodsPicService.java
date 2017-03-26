package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.GoodsPic;
import web.service.IGoodsPicService;

public class GoodsPicService extends ServiceImpl<GoodsPic, Serializable> implements IGoodsPicService<GoodsPic, Serializable> {

	public GoodsPicService(Class<GoodsPic> entityClass, HibernateEntityDao<GoodsPic, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}

}