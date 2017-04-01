package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.ExchangeOrder;
import web.service.IExchangeOrderService;

public class ExchangeOrderService extends ServiceImpl<ExchangeOrder, Serializable>
		implements IExchangeOrderService<ExchangeOrder, Serializable> {

	public ExchangeOrderService(Class<ExchangeOrder> entityClass, HibernateEntityDao<ExchangeOrder, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}
}