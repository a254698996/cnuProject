/**
 * 
 */
package com.hibernate.dao.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.hibernate.dao.base.Page;

import web.dao.hibernate.IGenericDao;

/**
 * 继承自spring的HibernateDaoSupport<br>
 * 提供了和具体实体类无关的数据库操作,如分页函数和若干便捷查询方法
 * 
 * @see HibernateDaoSupport
 */
@SuppressWarnings("rawtypes")
public class HibernateGenericDao extends HibernateDaoSupport implements IGenericDao {

	public HibernateGenericDao(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 分页查询函数，使用hql.
	 *
	 * @param pageNo
	 *            页号,从1开始.
	 */
	@SuppressWarnings("unchecked")
	public Page pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));

		List countlist = getHibernateTemplate().find(countQueryString, values);
		long totalCount = (Long) countlist.get(0);
		if (totalCount < 1)
			return new Page();
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = createQuery(hql, values);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
		return new Page(startIndex, totalCount, pageSize, list);
	}

	@Override
	public Page pagedQuery(String hql, int pageNo, int pageSize, Object[] propertyArr, Object[] propertyMatetype,
			Object[] propertyVal) {
		List<Object> list = null;
		StringBuffer sbf=new StringBuffer(hql);
		if (propertyArr != null && propertyMatetype != null && propertyVal != null) {
			list = new ArrayList<Object>();
			for (int i = 0; i < propertyArr.length; i++) {
				sbf.append(" and ").append(propertyArr[i]).append(getMatetype(propertyMatetype[i])) ;
				if(getMatetype(propertyMatetype[i]).contains("like")){
					sbf.append(getLikeseachStr(propertyVal[i]));
				}else{
					sbf.append(propertyVal[i]);
				}
			}
		}
		return this.pagedQuery(sbf.toString(), pageNo, pageSize, list == null ? null : list.toArray());
	}

	private String getMatetype(Object property) {
		switch (String.valueOf(property)) {
		case "eq":
			return " = ";
		case "like":
			return " like ";
		default:
			return null;
		}
	}

	private String getLikeseachStr(Object value) {
		return "%" + String.valueOf( value ) + "%";
	}

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
	@SuppressWarnings("unchecked")
	public Page dataQuery(String hql, int start, int pageSize, Object... values) {
		Assert.hasText(hql);
		// Count查询
		String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
		List countlist = getHibernateTemplate().find(countQueryString, values);
		long totalCount = (Long) countlist.get(0);
		if (totalCount < 1)
			return new Page();
		// 实际查询返回分页对象
		int startIndex = start;
		Query query = createQuery(hql, values);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
		return new Page(startIndex, totalCount, pageSize, list);
	}

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
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = currentSession().createQuery(hql);
		if(values!=null){
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * 根据hql查询,直接使用HibernateTemplate的find函数.
	 * 
	 * @param values
	 *            可变参数
	 */
	public List find(String hql, Object... values) {
		Assert.hasText(hql);
		return getHibernateTemplate().find(hql, values);
	}

	/**
	 * 执行一些必须的sql语句把内存中的对象同步到jdbc的链接中
	 */
	public void flush() {
		getHibernateTemplate().flush();
	}

	/**
	 * 清除所有对象缓存
	 */
	public void clear() {
		getHibernateTemplate().clear();
	}

	/**
	 * 执行本地sql语句获得标量数值列表
	 */
	public List executeNativeSql(String sql) {
		return currentSession().createSQLQuery(sql).list();
	}

	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 */
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order//s*by[//w|//W|//s|//S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
}