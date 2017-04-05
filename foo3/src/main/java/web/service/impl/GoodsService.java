package web.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hibernate.dao.base.Page;
import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.Goods;
import web.entity.GoodsCategory;
import web.service.IGoodsService;

public class GoodsService extends ServiceImpl<Goods, Serializable> implements IGoodsService<Goods, Serializable> {

	public GoodsService(Class<Goods> entityClass, HibernateEntityDao<Goods, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}

	@Override
	public Page getList(Integer pageIndex, int pageSize, Integer userId, String _SCH_name, Integer exchangeGroupId) {
		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}

		StringBuffer hql = new StringBuffer(
				"select g ,c ,subC from Goods g, GoodsCategory c, GoodsCategory subC where g.goodsCategoryCode=c.code and g.goodsCategorySubCode=subC.code ");
		Page page = null;

		List<Object> paramList = new ArrayList<Object>();

		if (StringUtils.isNotBlank(_SCH_name)) {
			hql.append(" and  g.name like ?");
			paramList.add("%" + _SCH_name + "%");
		}

		if (userId !=null) {
			hql.append(" and  g.userId = ?");
			paramList.add(userId);
		}

		if (exchangeGroupId != null) {
			hql.append(" and  g.exchangeGroupId = ?");
			paramList.add(exchangeGroupId);
		}

		page = pagedQuery(hql.toString(), pageIndex, Page.defaultPageSize, paramList.toArray());

		List<Object> list = page.getList();
		List<Object> goodsList = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			for (Object object : list) {
				Object[] objs = (Object[]) object;
				Goods goods = (web.entity.Goods) objs[0];
				GoodsCategory c = (web.entity.GoodsCategory) objs[1];
				GoodsCategory subC = (web.entity.GoodsCategory) objs[2];
				goods.setGoodsCategoryName(c.getName());
				goods.setGoodsCategorySubName(subC.getName());
				goodsList.add(goods);
			}
		}
		page.setList(goodsList);
		return page;
	}

}