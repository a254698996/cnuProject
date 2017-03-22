package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.GoodsCategory;
import web.service.IGoodsCategoryService;

public class GoodsCategoryService extends ServiceImpl<GoodsCategory, Serializable>
		implements IGoodsCategoryService<GoodsCategory, Serializable> {

	public GoodsCategoryService(Class<GoodsCategory> entityClass, HibernateEntityDao<GoodsCategory, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}

}