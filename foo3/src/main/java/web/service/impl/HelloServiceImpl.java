package web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import web.dao.hibernate.CP_HibernateDAO;
import web.model.City;
import web.service.HelloService;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
public class HelloServiceImpl implements HelloService {

	@Autowired
	@Lazy
	CP_HibernateDAO hibernateDAO;

	@Override
	public void save(City t) {
		hibernateDAO.save(t);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public City get(Integer k) {
		return (City) hibernateDAO.get(City.class, k);
	}
}
