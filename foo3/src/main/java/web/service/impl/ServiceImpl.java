package web.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import web.dao.hibernate.impl.HibernateEntityDao;
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

	@Override
	public List<T> getAll() {
		return hedao.getAll();
	}

	public void setHedao(HibernateEntityDao<T, Serializable> hedao) {
		this.hedao = hedao;
		hedao.setEntityClass(entityClass);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void remove(T entity) {
		hedao.remove(entity);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void removeById(PK id) {
		hedao.removeById(id);
	}

	@Override
	@Transactional(value=TxType.REQUIRED)
	public void removeAll(Collection<T> collection) {
		hedao.removeAll(collection);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void update(T entity) {
		hedao.update(entity);
	}

	@Override
	public String getIdName(Class<T> clazz) {
		return hedao.getIdName(clazz);
	}
}
