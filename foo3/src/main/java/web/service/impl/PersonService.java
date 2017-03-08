package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.extend.HibernateEntityDao;

import web.model.Person;
import web.service.IPersonService;

public class PersonService extends ServiceImpl<Person, Serializable> implements IPersonService<Person, Serializable> {

	public PersonService(Class<Person> entityClass, HibernateEntityDao<Person, Serializable> hedao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
	}

}
