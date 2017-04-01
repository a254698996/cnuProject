package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.GoodsScore;
import web.service.IGoodsScoreService;

public class GoodsScoreService extends ServiceImpl<GoodsScore, Serializable>
		implements IGoodsScoreService<GoodsScore, Serializable> {

	public GoodsScoreService(Class<GoodsScore> entityClass, HibernateEntityDao<GoodsScore, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}
}