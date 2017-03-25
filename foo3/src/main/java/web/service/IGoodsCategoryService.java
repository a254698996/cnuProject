package web.service;

import java.io.Serializable;
import java.util.List;

public interface IGoodsCategoryService<T, PK extends Serializable> extends IService<T, PK> {

	public List<Object> getPList();

	public List<Object> getSubList(String pcode);
}
