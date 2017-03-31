package web.service;

import java.io.Serializable;

import com.hibernate.dao.base.Page;

public interface IGoodsService<T, PK extends Serializable> extends IService<T, PK> {
	
	public Page getList(Integer pageNo, int pageSize,String userId ,String _SCH_name  );

}
