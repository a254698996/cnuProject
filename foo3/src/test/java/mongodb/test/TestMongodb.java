package mongodb.test;

import org.bson.Document;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoNamespace;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import mongodb.dao.IStudentDao;
import mongodb.dao.MongodbClient;
import mongodb.dao.Impl.StudentDaoImpl;
import mongodb.entity.Student;

public class TestMongodb {

	String databaseName;
	MongoClient mongoClient;
	IStudentDao<Student> studentDao;

	@Before
	public void startUp() {
		System.out.println("test start ...");
		databaseName = "emp";
		mongoClient = MongodbClient.getInstance().getMongoClient();
		studentDao = new StudentDaoImpl(databaseName, mongoClient);
	}

	@Test
	public void test() {
		testDropCollection();
		testInsert();
		testFind();
	}

	// @Test
	public void testInsert() {
		Student s = new Student();
		s.setAge(17);
		s.setEmail("tarzan.111@yahoo.com.cn");
		s.setName("李三");
		studentDao.insert(s);
	}

	// @Test
	public void testFind() {
		Student s = new Student();
		s.setAge(17);
		// s.setEmail("tarzan.111@yahoo.com.cn");
		// s.setName("李三");
		// s.setName("张四");
		// s.setAge(99);
		Student find = studentDao.find(s);
		System.out.println(find);
		Assert.assertEquals(s.getName(), find.getName());
		Assert.assertEquals(s.getAge(), find.getAge());
		Assert.assertEquals(s.getEmail(), find.getEmail());
	}

	// @Test
	public void testUpdate() throws CloneNotSupportedException {
		Student findT = new Student();
		findT.setAge(17);
		findT.setEmail("tarzan.111@yahoo.com.cn");
		findT.setName("李三");
		Student updateT = (Student) findT.clone();
		updateT.setName("张四");
		updateT.setAge(99);
		System.out.println(findT);
		System.out.println(updateT);
		long update = studentDao.update(findT, updateT);
		Assert.assertEquals(update, 1L);
	}

	// @Test
	public void testDropCollection() {
		System.out.println("test dropCollection ...");
		studentDao.drop(new Student());
		Student s = new Student();
		s.setAge(17);
		s.setEmail("tarzan.111@yahoo.com.cn");
		s.setName("李三");
		Student find = studentDao.find(s);
		Assert.assertNull(find);
	}

	@After
	public void after() {
		System.out.println("test end ...");
	}

	private static void hehe() {
		MongoDatabase database = MongodbClient.getInstance().getMongoClient().getDatabase("test");
		String name = database.getName();
		System.out.println("database  " + name);

		MongoCollection<Document> collection = database.getCollection("emp");
		MongoNamespace namespace = collection.getNamespace();
		String fullName = namespace.getFullName();
		System.out.println("fullName  " + fullName);

		ListCollectionsIterable<Document> listCollections = database.listCollections();
		MongoCursor<Document> iterator = listCollections.iterator();
		System.out.println("iterator  " + iterator);
		while (iterator.hasNext()) {
			Document next = iterator.next();
			System.out.println(next);
		}
	}
}
