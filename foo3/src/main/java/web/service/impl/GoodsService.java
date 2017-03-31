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
	public Page getList(Integer pageIndex, int pageSize, String userId,String _SCH_name ) {
		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}
		
		String hql="select g ,c ,subC from Goods g, GoodsCategory c, GoodsCategory subC where g.goodsCategoryCode=c.code and g.goodsCategorySubCode=subC.code and  g.name like ? ";
		Page page = null;
		if(StringUtils.isBlank(userId)){
			page = pagedQuery( hql, pageIndex, Page.defaultPageSize,new Object[]{ "%"+(_SCH_name==null?"":_SCH_name)+"%"});
		}else{
		    hql+=" and g.userId=? ";
			page = pagedQuery( hql, pageIndex, Page.defaultPageSize,new Object[]{ "%"+(_SCH_name==null?"":_SCH_name)+"%",userId});
		}
		
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