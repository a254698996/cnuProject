package web.service;

import java.io.Serializable;

import com.hibernate.dao.base.Page;

import web.entity.Goods;

public interface IGoodsService<T, PK extends Serializable> extends IService<T, PK> {

	public Page getList(Integer pageIndex, int pageSize, Integer userId, String _SCH_name,Integer exchangeGroupId,Integer grounding );
	
	
	public Page getList(Integer pageIndex, int pageSize, Integer userId, Integer grounding );


	public Goods getGoodsById(String id);
}
