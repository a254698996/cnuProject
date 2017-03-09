package web.service;

import java.io.Serializable;

import web.dao.hibernate.IEntityDao;

public interface IService<T, PK extends Serializable> extends IEntityDao<T, PK >{

}
