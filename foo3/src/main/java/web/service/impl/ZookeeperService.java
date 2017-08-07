package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.ZookeeperEntity;
import web.service.IZookeeperService;

public class ZookeeperService extends ServiceImpl<ZookeeperEntity, Serializable>
		implements IZookeeperService<ZookeeperEntity, Serializable> {

	public ZookeeperService(Class<ZookeeperEntity> entityClass, HibernateEntityDao<ZookeeperEntity, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}

}
