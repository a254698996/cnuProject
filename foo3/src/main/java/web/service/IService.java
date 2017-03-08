package web.service;

import java.io.Serializable;

public interface IService<T, PK extends Serializable> {
	public void save(T t);

	public T get(PK pk);

}
