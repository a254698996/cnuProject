package web.service;

import java.io.Serializable;

import web.dao.hibernate.IEntityDao;
import web.dao.hibernate.IGenericDao;

public interface IService<T, PK extends Serializable> extends IEntityDao<T, PK >,IGenericDao{

}
