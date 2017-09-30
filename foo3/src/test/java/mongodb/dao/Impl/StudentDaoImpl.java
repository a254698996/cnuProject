package mongodb.dao.Impl;

import com.mongodb.MongoClient;

import mongodb.dao.IStudentDao;
import mongodb.entity.Student;

public class StudentDaoImpl extends MongoDbDaoImpl<Student> implements IStudentDao<Student> {

	public StudentDaoImpl(String database, MongoClient mongoClient) {
		this.dataBase = database;
		this.mongoClient = mongoClient;
	}

}
