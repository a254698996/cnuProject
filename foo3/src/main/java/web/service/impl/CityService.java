package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.City;
import web.service.ICityService;

public class CityService extends ServiceImpl<City, Serializable> implements ICityService<City, Serializable> {

	public CityService(Class<City> entityClass, HibernateEntityDao<City, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}

}