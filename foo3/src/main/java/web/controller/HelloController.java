package web.controller;

import java.util.Date;
import java.util.UUID;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.model.City;
import web.service.HelloService;

@Controller
@RequestMapping("/")
public class HelloController {
	Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private HelloService helloService;

	@RequestMapping("addCity")
	public void addCity() {
		logger.info("request index " + new Date());
		City city = new City();
		city.setId(Math.round(99));
		city.setName(UUID.randomUUID().toString());
		city.setPassword("password_" + UUID.randomUUID().toString());
		helloService.save(city);
	}

	@RequestMapping("getCity/{id}")
	@ResponseBody
	public City getCity(@PathVariable("id") Integer id) {
		logger.info("request index " + new Date());
		return (City) helloService.get(id);
	}

	@RequestMapping("index")
	@ResponseBody
	public String index() {
		logger.info("request index " + new Date());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("version", "0.0.1_" + new Date());
		return jsonObject.toString();
	}
}
