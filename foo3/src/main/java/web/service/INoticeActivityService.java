package web.service;

import java.io.Serializable;
import java.util.List;

public interface INoticeActivityService<T, PK extends Serializable> extends IService<T, PK> {

	public List<Object> getIndexNoticeList(int pageSize);
	
	public List<Object> getIndexActivityList(int pageSize);

}
