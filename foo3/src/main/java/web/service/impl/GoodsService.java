package web.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.commons.lang.StringUtils;

import com.hibernate.dao.base.Page;
import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.Goods;
import web.entity.GoodsCategory;
import web.entity.GoodsPic;
import web.entity.User;
import web.service.IGoodsService;

public class GoodsService extends ServiceImpl<Goods, Serializable> implements IGoodsService<Goods, Serializable> {

	public GoodsService(Class<Goods> entityClass, HibernateEntityDao<Goods, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Goods getGoodsById(String id) {
		StringBuffer hql = new StringBuffer(
				"select g ,c ,subC,u from Goods g, GoodsCategory c, GoodsCategory subC,User u where g.goodsCategoryCode=c.code and g.goodsCategorySubCode=subC.code and g.userId=u.id and g.id=?  ");
		Page page = null;

		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);

		page = pagedQuery(hql.toString(), Page.defaultStartIndex, Page.defaultStartIndex, paramList.toArray());

		List<Object> list = page.getList();
		Goods returnGoods = null;
		if (list != null && !list.isEmpty()) {
			for (Object object : list) {
				Object[] objs = (Object[]) object;
				returnGoods = (web.entity.Goods) objs[0];
				GoodsCategory c = (web.entity.GoodsCategory) objs[1];
				GoodsCategory subC = (web.entity.GoodsCategory) objs[2];
				User user = (web.entity.User) objs[3];
				returnGoods.setGoodsCategoryName(c.getName());
				returnGoods.setGoodsCategorySubName(subC.getName());
				returnGoods.setUser(user);
			}
		}

		if (returnGoods != null) {
			Page pagedQuery = pagedQuery("from GoodsPic p where p.goodsId=? ", Page.defaultStartIndex,
					Page.defaultPageSize, new Object[] { returnGoods.getId() });
			if (pagedQuery.getList() != null && !pagedQuery.getList().isEmpty()) {
				GoodsPic goodsPic = (GoodsPic) pagedQuery.getList().get(0);
				returnGoods.getGoodsPicList().add(goodsPic);
			}
		}
		return returnGoods;
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Page getList(Integer pageIndex, int pageSize, Integer userId, String _SCH_name, Integer exchangeGroupId,
			Integer grounding) {
		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}

		StringBuffer hql = new StringBuffer(
				"select g ,c ,subC from Goods g, GoodsCategory c, GoodsCategory subC where g.goodsCategoryCode=c.code and g.goodsCategorySubCode=subC.code   ");
		Page page = null;

		List<Object> paramList = new ArrayList<Object>();

		if (StringUtils.isNotBlank(_SCH_name)) {
			hql.append(" and  g.name like ?");
			paramList.add("%" + _SCH_name + "%");
		}

		if (userId != null) {
			hql.append(" and  g.userId = ?");
			paramList.add(userId);
		}

		if (exchangeGroupId != null) {
			hql.append(" and  g.exchangeGroupId = ?");
			paramList.add(exchangeGroupId);
		}

		if (grounding != null) {
			hql.append(" and  g.state = ?");
			paramList.add(grounding);
		}

		page = pagedQuery(hql.toString(), pageIndex, (pageSize == 0) ? Page.defaultPageSize : pageSize,
				paramList.toArray());

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

	@Override
	public Page getList(Integer pageIndex, int pageSize, Integer userId, Integer grounding) {

		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}

		StringBuffer hql = new StringBuffer(
				"select g ,c ,subC from Goods g, GoodsCategory c, GoodsCategory subC where g.goodsCategoryCode=c.code and g.goodsCategorySubCode=subC.code and g.deal=0 ");
		Page page = null;

		List<Object> paramList = new ArrayList<Object>();

		if (userId != null) {
			hql.append(" and  g.userId = ?");
			paramList.add(userId);
		}
 
		if (grounding != null) {
			hql.append(" and  g.state = ?");
			paramList.add(grounding);
		}

		page = pagedQuery(hql.toString(), pageIndex, (pageSize == 0) ? Page.defaultPageSize : pageSize,
				paramList.toArray());

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