package web.dao.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

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

	/**
	 * 获取Entity对象的主键名.
	 */
	String getIdName(Class<T> clazz);
}