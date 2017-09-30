package mongodb.dao.Impl;

import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.result.UpdateResult;

import mongodb.dao.IMongoDbDao;
import util.JsonUtils2;

public abstract class MongoDbDaoImpl<T> implements IMongoDbDao<T> {

	protected MongoClient mongoClient;

	protected String dataBase;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Document toDocument(T t) {
		String obj2json = JsonUtils2.obj2json(t);
		Map json2map = JsonUtils2.json2map(obj2json);
		return new Document(json2map);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected T toObject(Document d, Class clazz) {
		try {
			if (d == null) {
				return null;
			}
			return (T) JsonUtils2.json2pojo(d.toJson(), clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected String getCollectName(T t) {
		Class<? extends Object> clazz = t.getClass();
		return clazz.getName();
	}

	@Override
	public void drop(T t) {
		String collectName = getCollectName(t);
		MongoCollection<Document> collection = mongoClient.getDatabase(dataBase).getCollection(collectName);
		collection.drop();
	}

	@Override
	public void insert(T t) {
		String collectName = getCollectName(t);
		MongoCollection<Document> collection = mongoClient.getDatabase(dataBase).getCollection(collectName);
		collection.insertOne(toDocument(t));
		String indexName = "age";
		if (!hasIndex(collection, indexName)) {
			createIndex(collection, indexName);
		}
	}

	private boolean hasIndex(MongoCollection<Document> collection, String indexName) {
		ListIndexesIterable<Document> listIndexes = collection.listIndexes();
		MongoCursor<Document> iterator = listIndexes.iterator();
		while (iterator.hasNext()) {
			Document next = iterator.next();
			Object object = next.get("name");
			if (object.equals(indexName)) {
				return true;
			}
		}
		return false;
	}

	private void createIndex(MongoCollection<Document> collection, String indexName) {
		String createIndex = collection.createIndex(new Document(indexName, 1), new IndexOptions().unique(true));
		System.out.println(" create index " + createIndex);
	}

	@Override
	public long update(T findT, T updateT) {
		String collectName = getCollectName(findT);
		MongoCollection<Document> collection = mongoClient.getDatabase(dataBase).getCollection(collectName);
		Document filter = toDocument(findT);
		Document update = toDocument(updateT);
		UpdateResult updateOne = collection.replaceOne(filter, update);
		return updateOne.getMatchedCount();
	}

	@Override
	public T find(T t) {
		String collectName = getCollectName(t);
		MongoCollection<Document> collection = mongoClient.getDatabase(dataBase).getCollection(collectName);
		Document document = toDocument(t);
		FindIterable<Document> find = collection.find(document);
		Document first = find.first();
		return toObject(first, t.getClass());
	}

	@Override
	public void delete(T t) {

	}

}
