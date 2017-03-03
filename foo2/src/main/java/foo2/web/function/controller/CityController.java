package foo2.web.function.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import foo2.web.function.model.City;
import foo2.web.function.service.CityService;

@Controller
@RequestMapping("/")
public class CityController {
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	@Lazy
	private CityService cityService;

	@RequestMapping("cityIndex")
	@ResponseBody
	public String index() {
		return new JSONObject().put("city", cityService.getCityName()).toString();
	}

	@RequestMapping("addCity")
	@ResponseBody
	public void addCity() {

	}

	@RequestMapping("allCityCount")
	@ResponseBody
	public String getAllCity() {
		List<City> findAll = cityService.findAll();
		int size = findAll.size();
		return new JSONObject().put("city count ", size).toString();
	}
}
