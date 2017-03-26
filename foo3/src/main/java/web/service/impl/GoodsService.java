package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.Goods;
import web.service.IGoodsService;

public class GoodsService extends ServiceImpl<Goods, Serializable> implements IGoodsService<Goods, Serializable> {

	public GoodsService(Class<Goods> entityClass, HibernateEntityDao<Goods, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}

}