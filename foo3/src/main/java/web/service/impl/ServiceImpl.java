package web.service.impl;

import java.io.Serializable;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.hibernate.dao.extend.HibernateEntityDao;

import web.service.IService;

public class ServiceImpl<T, PK extends Serializable> implements IService<T, PK> {
	protected Class<T> entityClass;// DAO所管理的Entity类型.
	private HibernateEntityDao<T, Serializable> hedao;

	public ServiceImpl(Class<T> entityClass, HibernateEntityDao<T, Serializable> hedao) {
		this.entityClass = entityClass;
		this.hedao = hedao;
	}

	public ServiceImpl() {
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void save(T t) {
		hedao.save(t);
	}

	@Override
	public T get(PK pk) {
		return hedao.get(pk);
	}

	public void setHedao(HibernateEntityDao<T, Serializable> hedao) {
		this.hedao = hedao;
		hedao.setEntityClass(entityClass);
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
}
