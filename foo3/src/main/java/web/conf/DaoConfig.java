package web.conf;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.hibernate.dao.extend.HibernateEntityDao;

import web.model.City;
import web.model.Person;
import web.service.ICityService;
import web.service.IPersonService;
import web.service.impl.CityService;
import web.service.impl.PersonService;

@Configuration
public class DaoConfig {
	private static final Logger logger = Logger.getLogger(DaoConfig.class);

	@Autowired
	SessionFactory sessionFactory;

	@Bean
	@Scope(value = "prototype")
	public HibernateEntityDao<?, ?> getHibernateEntityDao() {
		return new HibernateEntityDao<>(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public IPersonService<Person, Serializable> getPersonService() {
		logger.info("IPersonService  created");
		return new PersonService(Person.class, (HibernateEntityDao<Person, Serializable>) getHibernateEntityDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public ICityService<City, Serializable> getCityService() {
		logger.info("ICityService  created");
		return new CityService(City.class, (HibernateEntityDao<City, Serializable>) getHibernateEntityDao());
	}

}
