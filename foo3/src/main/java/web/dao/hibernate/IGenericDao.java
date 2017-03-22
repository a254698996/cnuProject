package web.dao.hibernate;

import java.util.List;

import org.hibernate.Query;

import com.hibernate.dao.base.Page;

public interface IGenericDao {

	/**
	 * 分页查询函数，使用hql.
	 *
	 * @param pageNo
	 *            页号,从1开始.
	 */
	public Page pagedQuery(String hql, int pageNo, int pageSize, Object... values);

	/**
	 * @param hql
	 *            查询sql
	 * @param start
	 *            分页从哪一条数据开始
	 * @param pageSize
	 *            每一个页面的大小
	 * @param values
	 *            查询条件
	 * @return page对象
	 */
	public Page dataQuery(String hql, int start, int pageSize, Object... values);

	/**
	 * 创建Query对象.
	 * 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 * 留意可以连续设置,如下：
	 * 
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 
	 * 调用方式如下：
	 * 
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 *
	 * @param values
	 *            可变参数.
	 */
	public Query createQuery(String hql, Object... values);

	/**
	 * 根据hql查询,直接使用HibernateTemplate的find函数.
	 * 
	 * @param values
	 *            可变参数
	 */
	@SuppressWarnings("rawtypes")
	public List find(String hql, Object... values);

	/**
	 * 执行一些必须的sql语句把内存中的对象同步到jdbc的链接中
	 */
	public void flush();

	/**
	 * 清除所有对象缓存
	 */
	public void clear();

	/**
	 * 执行本地sql语句获得标量数值列表
	 */
	@SuppressWarnings("rawtypes")
	public List executeNativeSql(String sql);
}
