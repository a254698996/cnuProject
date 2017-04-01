package web.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

import com.hibernate.dao.base.Page;
import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.service.IService;

public class ServiceImpl<T, PK extends Serializable> implements IService<T, PK> {
	protected Class<T> entityClass;// DAO所管理的Entity类型.
	protected HibernateEntityDao<T, Serializable> hedao;
	protected HibernateGenericDao hdao;

	public ServiceImpl(Class<T> entityClass, HibernateEntityDao<T, Serializable> hedao, HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.hedao = hedao;
		this.hdao = hdao;
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
	@Transactional(value = TxType.REQUIRED)
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

	@Override
	public List<T> findBy(String propertyName, Object value) {
		return hedao.findBy(propertyName, value);
	}

	@Override
	public List<T> findBy(String propertyName, Object value, String orderBy, boolean isAsc) {
		return hedao.findBy(propertyName, value, orderBy, isAsc);
	}

	@Override
	public T findUniqueBy(String propertyName, Object value) {
		return hedao.findUniqueBy(propertyName, value);
	}

	@Override
	public Page pagedQuery(Criteria criteria, int pageNo, int pageSize) {
		return hedao.pagedQuery(criteria, pageNo, pageSize);
	}

	@Override
	public Page pagedQuery(int pageNo, int pageSize, Criterion... criterions) {
		return hedao.pagedQuery(pageNo, pageSize, criterions);
	}

	@Override
	public Page pagedQuery(int pageNo, int pageSize, String orderBy, boolean isAsc, Criterion... criterions) {
		return hedao.pagedQuery(pageNo, pageSize, orderBy, isAsc, criterions);
	}

	@Override
	public boolean isUnique(T entity, Serializable uniquePropertyNamesParam) {
		return hedao.isUnique(entity, uniquePropertyNamesParam);
	}

	@Override
	public Criteria getCriteria() {
		return hedao.getCriteria();
	}

	@Override
	public Criteria createCriteria(Criterion... criterions) {
		return hedao.createCriteria(criterions);
	}

	@Override
	public T queryBeanByHql(T clazz) {
		return hedao.queryBeanByHql(clazz);
	}

	@Override
	public Page pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
		return hdao.pagedQuery(hql, pageNo, pageSize, values);
	}

	@Override
	public Page pagedQuery(String hql, int pageNo, int pageSize, Object[] propertyArr, Object[] propertyMatetype,
			Object[] propertyVal) {
		return hdao.pagedQuery(hql, pageNo, pageSize, propertyArr,  propertyMatetype, propertyVal);
	}

	@Override
	public Page dataQuery(String hql, int start, int pageSize, Object... values) {
		return hdao.dataQuery(hql, start, pageSize, values);
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		return hdao.createQuery(hql, values);
	}

	@Override
	public List find(String hql, Object... values) {
		return hdao.find(hql, values);
	}

	@Override
	public void flush() {
		hdao.flush();
	}

	@Override
	public void clear() {
		hdao.clear();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List executeNativeSql(String sql) {
		return hdao.executeNativeSql(sql);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void update(String entityName, T entity) {
		hedao.update(entityName, entity);
	}
	
}
