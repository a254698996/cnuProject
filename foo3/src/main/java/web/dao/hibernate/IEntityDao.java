package web.dao.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import com.hibernate.dao.base.Page;

/**
 * 针对单个Entity对象的CRUD操作定义.
 */
public interface IEntityDao<T, PK extends Serializable> {
	T get(PK id);

	List<T> getAll();

	void save(T entity);

	void remove(T entity);

	void removeById(PK id);

	void removeAll(Collection<T> collection);
	
	void update(T entity);
	
	public List<T> findBy(String propertyName, Object value);
 
	public List<T> findBy(String propertyName, Object value, String orderBy, boolean isAsc);
 
	public T findUniqueBy(String propertyName, Object value) ;
	
	public Page pagedQuery(Criteria criteria, int pageNo, int pageSize);
	
	public Page pagedQuery(int pageNo, int pageSize, Criterion... criterions) ;
	
	public Page pagedQuery(int pageNo, int pageSize, String orderBy, boolean isAsc, Criterion... criterions);
	
	public boolean isUnique(T entity, Serializable uniquePropertyNamesParam);
	
	public Criteria getCriteria();
	
	public Criteria createCriteria(Criterion... criterions) ;
	
	public T queryBeanByHql(T clazz);

	/**
	 * 获取Entity对象的主键名.
	 */
	String getIdName(Class<T> clazz);
}