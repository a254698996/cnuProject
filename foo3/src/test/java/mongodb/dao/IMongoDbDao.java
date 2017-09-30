package mongodb.dao;

public interface IMongoDbDao<T> {

	public void insert(T t);

	public void drop(T t);

	public T find(T t);

	public long update(T findT, T updateT);

	public void delete(T t);

}
