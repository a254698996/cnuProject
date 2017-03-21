package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.Person;
import web.service.IPersonService;

public class PersonService extends ServiceImpl<Person, Serializable> implements IPersonService<Person, Serializable> {

	public PersonService(Class<Person> entityClass, HibernateEntityDao<Person, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}

}
