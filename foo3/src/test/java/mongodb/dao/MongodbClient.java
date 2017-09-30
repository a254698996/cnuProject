package mongodb.dao;

import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class MongodbClient {

	private MongoClient mongoClient;

	private static MongodbClient mc = new MongodbClient();

	private MongodbClient() {
		mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017)));
	}

	public static MongodbClient getInstance() {
		return mc;
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

}
