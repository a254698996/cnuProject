package web.service;

import java.util.List;

public interface BaseService<K, T> {

	public void save(T t);

	public T get(K k);

}
