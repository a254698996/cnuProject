package web.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import web.dao.hibernate.CP_HibernateDAO;
import web.model.City;
import web.service.ICityService;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
public class CityServiceImpl implements ICityService {

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

	@Override
	public void delete(Integer k) {
		hibernateDAO.deleteByKey(k, City.class);
	}

	@Override
	public void Update(City t) {
		hibernateDAO.update(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getAll() {
		return (List<City>) hibernateDAO.findAll(City.class);
	}

	@Override
	public void deleteAll() {
		Collection<City> all = this.getAll();
		hibernateDAO.deleteAll(all);
	}

}
