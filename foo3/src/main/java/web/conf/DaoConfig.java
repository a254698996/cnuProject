package web.conf;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.City;
import web.entity.Goods;
import web.entity.GoodsCategory;
import web.entity.GoodsPic;
import web.entity.Menu;
import web.entity.Person;
import web.entity.User;
import web.service.ICityService;
import web.service.IGoodsCategoryService;
import web.service.IGoodsPicService;
import web.service.IGoodsService;
import web.service.IMenuService;
import web.service.IPersonService;
import web.service.IUserService;
import web.service.impl.CityService;
import web.service.impl.GoodsCategoryService;
import web.service.impl.GoodsPicService;
import web.service.impl.GoodsService;
import web.service.impl.MenuService;
import web.service.impl.PersonService;
import web.service.impl.UserService;

@Configuration
public class DaoConfig {
	private static final Logger logger = Logger.getLogger(DaoConfig.class);

	@Autowired
	SessionFactory sessionFactory;

	@Bean
	public HibernateGenericDao getHibernateGenericDao() {
		return new HibernateGenericDao(sessionFactory);
	}

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
		return new PersonService(Person.class, (HibernateEntityDao<Person, Serializable>) getHibernateEntityDao(),
				getHibernateGenericDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public ICityService<City, Serializable> getCityService() {
		logger.info("ICityService  created");
		return new CityService(City.class, (HibernateEntityDao<City, Serializable>) getHibernateEntityDao(),
				getHibernateGenericDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public IUserService<User, Serializable> getUserService() {
		logger.info("IUserService  created");
		return new UserService(User.class, (HibernateEntityDao<User, Serializable>) getHibernateEntityDao(),
				getHibernateGenericDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public IGoodsCategoryService<GoodsCategory, Serializable> getGoodsCategoryService() {
		logger.info("IGoodsCategoryService  created");
		return new GoodsCategoryService(GoodsCategory.class,
				(HibernateEntityDao<GoodsCategory, Serializable>) getHibernateEntityDao(), getHibernateGenericDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public IGoodsService<Goods, Serializable> getGoodsService() {
		logger.info("IGoodsService  created");
		return new GoodsService(Goods.class, (HibernateEntityDao<Goods, Serializable>) getHibernateEntityDao(),
				getHibernateGenericDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public IGoodsPicService<GoodsPic, Serializable> getGoodsPicService() {
		logger.info("IGoodsPicService  created");
		return new GoodsPicService(GoodsPic.class, (HibernateEntityDao<GoodsPic, Serializable>) getHibernateEntityDao(),
				getHibernateGenericDao());
	}
	
	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public IMenuService<Menu, Serializable> getMenuService() {
		logger.info("IMenuService  created");
		return new MenuService(Menu.class, (HibernateEntityDao<Menu, Serializable>) getHibernateEntityDao(),
				getHibernateGenericDao());
	}

}
