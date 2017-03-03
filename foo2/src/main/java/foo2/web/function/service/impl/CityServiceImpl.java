package foo2.web.function.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import foo2.dao.hibernate.CP_HibernateDAO;
import foo2.web.function.model.City;
import foo2.web.function.service.CityService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, transactionManager = "transactionManager")
public class CityServiceImpl implements CityService {
	private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);
	

	@Resource(name = "hibernateDAO")
	@Lazy
	private CP_HibernateDAO dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<City> findAll() {
		logger.info("city service  get all ");
		return (List<City>) dao.findAll(City.class);
	}

	@Override
	public City getById(long id) {
		return (City) dao.get(City.class, id);
	}

	@Override
	public void save(City City) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(City City) {
		// TODO Auto-generated method stub

	}

	@Override
	public City getByName(long id, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCityName() {
		return "cityName : " + UUID.randomUUID();
	}

}
