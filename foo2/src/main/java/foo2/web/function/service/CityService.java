package foo2.web.function.service;

import java.util.List;

import foo2.web.function.model.City;
 

public interface CityService {

	public String getCityName();

	public List<City> findAll();

	public City getById(long id);

	public void save(City City);

	public void delete(City City);

	public City getByName(long id, String name);
}
