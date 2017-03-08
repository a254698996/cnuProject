package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.extend.HibernateEntityDao;

import web.model.City;
import web.service.ICityService;

public class CityService extends ServiceImpl<City, Serializable> implements ICityService<City, Serializable> {

	public CityService(Class<City> entityClass, HibernateEntityDao<City, Serializable> hedao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
	}

}