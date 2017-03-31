package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.ExchangeGroup;
import web.service.IExchangeGroupService;

public class ExchangeGroupService extends ServiceImpl<ExchangeGroup, Serializable> implements IExchangeGroupService<ExchangeGroup, Serializable> {

	public ExchangeGroupService(Class<ExchangeGroup> entityClass, HibernateEntityDao<ExchangeGroup, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}
}