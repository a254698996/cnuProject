package web.service.impl;

import java.io.Serializable;
import java.util.List;

import com.hibernate.dao.base.Page;
import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.GoodsCategory;
import web.service.IGoodsCategoryService;

public class GoodsCategoryService extends ServiceImpl<GoodsCategory, Serializable>
		implements IGoodsCategoryService<GoodsCategory, Serializable> {

	public GoodsCategoryService(Class<GoodsCategory> entityClass, HibernateEntityDao<GoodsCategory, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}

	@Override
	public List<Object> getPList() {
		Page page = pagedQuery("from GoodsCategory g where g.pcode is null ", 1, Integer.MAX_VALUE);
		return page.getList();
	}

	@Override
	public List<Object> getSubList(String pcode) {
		Page page = pagedQuery("from GoodsCategory g where g.pcode=?", 1, Integer.MAX_VALUE, new Object[] { pcode });
		return page.getList();
	}

}