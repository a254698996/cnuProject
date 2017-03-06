package web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import web.dao.hibernate.CP_HibernateDAO;
import web.service.IBaseService;

public class BaseDaoImpl<K, Base> implements IBaseService<K, Base> {

	@Autowired
	@Lazy
	CP_HibernateDAO hibernateDAO;

	@Override
	public void save(Base t) {
		hibernateDAO.save(t);
	}

	@Override
	public Base get(K k) {
		return hibernateDAO.get(Base.class, k);
	}

	@Override
	public void delete(K k) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Update(Base t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Base> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
