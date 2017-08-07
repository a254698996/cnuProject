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
import web.entity.ExchangeGroup;
import web.entity.ExchangeOrder;
import web.entity.Goods;
import web.entity.GoodsCategory;
import web.entity.GoodsPic;
import web.entity.GoodsScore;
import web.entity.Menu;
import web.entity.NoticeActivity;
import web.entity.Permission;
import web.entity.Person;
import web.entity.Role;
import web.entity.RolePermission;
import web.entity.SeqnoEntity;
import web.entity.User;
import web.entity.UserRole;
import web.entity.ZookeeperEntity;
import web.service.ICityService;
import web.service.IExchangeGroupService;
import web.service.IExchangeOrderService;
import web.service.IGoodsCategoryService;
import web.service.IGoodsPicService;
import web.service.IGoodsScoreService;
import web.service.IGoodsService;
import web.service.IMenuService;
import web.service.INoticeActivityService;
import web.service.IPermissionService;
import web.service.IPersonService;
import web.service.IRolePermissionService;
import web.service.IRoleService;
import web.service.ISeqnoService;
import web.service.IUserRoleService;
import web.service.IUserService;
import web.service.IZookeeperService;
import web.service.impl.CityService;
import web.service.impl.ExchangeGroupService;
import web.service.impl.ExchangeOrderService;
import web.service.impl.GoodsCategoryService;
import web.service.impl.GoodsPicService;
import web.service.impl.GoodsScoreService;
import web.service.impl.GoodsService;
import web.service.impl.MenuService;
import web.service.impl.NoticeActivityService;
import web.service.impl.PermissionService;
import web.service.impl.PersonService;
import web.service.impl.RolePermissionService;
import web.service.impl.RoleService;
import web.service.impl.SeqnoService;
import web.service.impl.UserRoleService;
import web.service.impl.UserService;
import web.service.impl.ZookeeperService;

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
	public IExchangeGroupService<ExchangeGroup, Serializable> getExchangeGroupService() {
		logger.info("IExchangeGroupService  created");
		return new ExchangeGroupService(ExchangeGroup.class,
				(HibernateEntityDao<ExchangeGroup, Serializable>) getHibernateEntityDao(), getHibernateGenericDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public IMenuService<Menu, Serializable> getMenuService() {
		logger.info("IMenuService  created");
		return new MenuService(Menu.class, (HibernateEntityDao<Menu, Serializable>) getHibernateEntityDao(),
				getHibernateGenericDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public IGoodsScoreService<GoodsScore, Serializable> getGoodsScoreService() {
		logger.info("IGoodsScoreService  created");
		return new GoodsScoreService(GoodsScore.class,
				(HibernateEntityDao<GoodsScore, Serializable>) getHibernateEntityDao(), getHibernateGenericDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public IExchangeOrderService<ExchangeOrder, Serializable> getExchangeOrderService() {
		logger.info("IExchangeOrderService  created");
		return new ExchangeOrderService(ExchangeOrder.class,
				(HibernateEntityDao<ExchangeOrder, Serializable>) getHibernateEntityDao(), getHibernateGenericDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public IRoleService<Role, Serializable> getRoleService() {
		logger.info("IRoleService  created");
		return new RoleService(Role.class, (HibernateEntityDao<Role, Serializable>) getHibernateEntityDao(),
				getHibernateGenericDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public IPermissionService<Permission, Serializable> getPermissionService() {
		logger.info("IPermissionService  created");
		return new PermissionService(Permission.class,
				(HibernateEntityDao<Permission, Serializable>) getHibernateEntityDao(), getHibernateGenericDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public IUserRoleService<UserRole, Serializable> getUserRoleService() {
		logger.info("IUserRoleService  created");
		return new UserRoleService(UserRole.class, (HibernateEntityDao<UserRole, Serializable>) getHibernateEntityDao(),
				getHibernateGenericDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public IRolePermissionService<RolePermission, Serializable> getRolePermissionService() {
		logger.info("IRolePermissionService  created");
		return new RolePermissionService(RolePermission.class,
				(HibernateEntityDao<RolePermission, Serializable>) getHibernateEntityDao(), getHibernateGenericDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	public INoticeActivityService<NoticeActivity, Serializable> getNoticeActivityService() {
		logger.info("INoticeActivityService  created");
		return new NoticeActivityService(NoticeActivity.class,
				(HibernateEntityDao<NoticeActivity, Serializable>) getHibernateEntityDao(), getHibernateGenericDao());
	}

	@SuppressWarnings("unchecked")
	@Bean
	public IZookeeperService<ZookeeperEntity, Serializable> getZookeeperService() {
		logger.info("IZookeeperService  created");
		return new ZookeeperService(ZookeeperEntity.class,
				(HibernateEntityDao<ZookeeperEntity, Serializable>) getHibernateEntityDao(), getHibernateGenericDao());
	}
	
	@SuppressWarnings("unchecked")
	@Bean
	public ISeqnoService<SeqnoEntity, Serializable> getSeqnoService() {
		logger.info("ISeqnoService  created");
		return new SeqnoService(SeqnoEntity.class,
				(HibernateEntityDao<SeqnoEntity, Serializable>) getHibernateEntityDao(), getHibernateGenericDao());
	}
}
