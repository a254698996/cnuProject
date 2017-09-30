package mongodb;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.result.DeleteResult;

import mongodb.entity.UserData;
import util.JsonUtils2;

public class TestMongoDriver {

	private Map map;

	@Test
	public void testCRUD() throws Exception {
		// 连接到mongodb
		// Mongo mongo = new Mongo("localhost", 27017);
		// MongoClient mongoClient = new MongoClient(Arrays.asList(
		// new ServerAddress("localhost", 27017),
		// new ServerAddress("localhost", 27018),
		// new ServerAddress("localhost", 27019)));

		MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017)));

		// 打开数据库test
		// MongoDatabase database = mongoClient.getDatabase("test");
		MongoDatabase db = mongoClient.getDatabase("test");

		// 遍历所有集合的名字
		MongoIterable<String> listCollectionNames = db.listCollectionNames();

		for (String s : listCollectionNames) {
			System.out.println("11111  " + s);
			// 先删除所有Collection(类似于关系数据库中的"表")
			if (!s.equals("system.indexes")) {
				System.out.println(" drop  " + s);
				db.getCollection(s).drop();
			}
		}

		// 取得集合emp(若：emp不存在，mongodb将自动创建该集合)
		MongoCollection<Document> coll = db.getCollection("emp");
		// delete all
		FindIterable<Document> find = coll.find();
		Document first = find.first();

		if (first != null) {
			coll.deleteMany(first);
		} else {
			System.out.println("emp  not exist ");
		}

		// create
		// BasicDBObject doc = new BasicDBObject("name", "杨俊明").append("sex",
		// "男").append("address",
		// new BasicDBObject("postcode", "201202").append("street",
		// "田林路888号").append("city", "上海"));
		// BsonDocument bsonDocument = doc.toBsonDocument(null, null);

		UserData userData2 = getUserData();
		// DBObject dbObject = toDBObject(userData2);
		System.out.println("dbObject  " + userData2);
		coll.insertOne(new Document(userData2.toMap()));

		// retrieve
		BasicDBObject docFind = new BasicDBObject("userName", "小明");
		Document findUserData = findUserData(coll, docFind);
		UserData updateUser = JsonUtils2.json2pojo(findUserData.toJson(), UserData.class);

		updateUser.setPassword("888888");

		coll.replaceOne(docFind, new Document(updateUser.toMap()));

		findUserData = findUserData(coll, docFind);
		System.out.println("findUserData " + findUserData);

		DeleteResult deleteMany = coll.deleteMany(docFind);
		System.out.println("deleteMany  " + deleteMany);
		////
		// if (findUserData != null) {
		// String json = findUserData.toJson();
		// UserData json2pojo = JSONUtils.json2pojo(json, UserData.class);
		// json2pojo.setUserName("小明AAAA");
		// map = json2pojo.toMap();
		// coll.updateOne(docFind, new Document(map));
		// }

		// BasicDBObject docFind2 = new BasicDBObject("userName", "小明AAAA");
		// Document findUserData2 = findUserData(coll, docFind2);
		// System.out.println("findUserData2 " + findUserData2);

		// // update
		// doc.put("sex", "MALE");// 把sex属性从"男"，改成"MALE"
		// coll.update(docFind, doc);
		// findResult = coll.findOne(docFind);
		// System.out.println(findResult);
		//
		// coll.dropIndexes();// 先删除所有索引
		// // create index
		// coll.createIndex(new BasicDBObject("name", 1)); // 1代表升序
		//
		// // 复杂对象
		// UserData userData = new UserData("jimmy", "123456");
		// Set<String> pets = new HashSet<String>();
		// pets.add("cat");
		// pets.add("dog");
		// Map<String, String> favoriteMovies = new HashMap<String, String>();
		// favoriteMovies.put("dragons", "Dragons II");
		// favoriteMovies.put("avator", "Avator I");
		// userData.setFavoriteMovies(favoriteMovies);
		// userData.setPets(pets);
		// userData.setBirthday(getDate(1990, 5, 1));
		// BasicDBObject objUser = new BasicDBObject("key",
		// "jimmy").append("value", toDBObject(userData));
		// coll.insert(objUser);
		// System.out.println(coll.findOne(objUser));

		// DBObject dbObject2 = toDBObject(userData2);

		// Map map2 = userData2.toMap();
		//
		// try {
		// UserData u = toUserData(new Document(map2));
		// System.out.println(u);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	private Document findUserData(MongoCollection<Document> coll, BasicDBObject docFind) {
		FindIterable<Document> find2 = coll.find(docFind);
		return find2.first();
	}

	private void printIterator(MongoCursor<Document> iterator) {
		while (iterator.hasNext()) {
			Document next = iterator.next();
			System.out.println(next);
		}
	}

	private UserData toUserData(Document db) throws Exception {
		return JsonUtils2.json2pojo(db.toJson(), UserData.class);
	}

	/**
	 * 获取指定日期
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private Date getDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month - 1, day);
		return calendar.getTime();
	}

	private UserData getUserData() {
		UserData ud = new UserData();
		ud.setBirthday(new Date());
		ud.setUserName("小明");
		Map favoriteMovies = new HashMap();
		favoriteMovies.put("1", "黑客帝国");
		favoriteMovies.put("2", "慌岛余生");
		favoriteMovies.put("3", "疯狂原始人");
		favoriteMovies.put("4", "慌野猎人");
		ud.setFavoriteMovies(favoriteMovies);
		Set<String> pets = new HashSet<String>();
		pets.add("cat");
		pets.add("dog");
		ud.setPets(pets);
		ud.setPassword("123456");
		return ud;
	}

}