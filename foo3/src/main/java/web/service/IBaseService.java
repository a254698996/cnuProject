package web.service;

import java.util.List;

public interface IBaseService<K, T> {

	public void save(T t);

	public T get(K k);

	public void delete(K k);

	public void Update(T t);

	public List<T> getAll();
	
	public void deleteAll();

}
