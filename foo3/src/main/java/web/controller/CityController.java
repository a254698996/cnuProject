package web.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import web.entity.City;
import web.service.ICityService;

@RestController
public class CityController {
	Logger logger = LoggerFactory.getLogger(CityController.class);

	@Autowired
	private ICityService<City, Serializable> cityService;

	@RequestMapping(value = "/addCity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addCity(@RequestBody City city, UriComponentsBuilder ucBuilder) {
		logger.info(" addCity  name :" + city.getName());
		cityService.save(city);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(city.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/city/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<City>> listCity() {
		List<City> cityList = cityService.getAll();
		if (cityList.isEmpty()) {
			return new ResponseEntity<List<City>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<City>>(cityList, HttpStatus.OK);
	}

	@RequestMapping(value = "/city/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<City> getCity(@PathVariable("id") Integer id) {
		City city = cityService.get(id);
		if (city == null) {
			return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
		}
		logger.info("city/{id} " + id);
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateCity/{id}", method = RequestMethod.PUT)
	public ResponseEntity<City> updateCity(@PathVariable("id") Integer id, @RequestBody City city) {
		City currCity = cityService.get(id);
		if (currCity == null) {
			return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
		}
		currCity.setName(city.getName());
		currCity.setPassword(city.getPassword());

		cityService.update(currCity);

		return new ResponseEntity<City>(city, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteCity/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<City> deleteCity(@PathVariable("id") Integer id) {
		City city = cityService.get(id);
		if (city == null) {
			return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
		}
		cityService.removeById(id);
		return new ResponseEntity<City>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/deleteCity/", method = RequestMethod.DELETE)
	public ResponseEntity<City> deleteCitys() {
		logger.info("delete all Citys");
		List<City> all = cityService.getAll();
		cityService.removeAll(all);
		return new ResponseEntity<City>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping("/index")	
	@ResponseBody
	public String index() {
		logger.info("request index " + new Date());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("version", "0.0.1_" + new Date());
		return jsonObject.toString();
	}
}
