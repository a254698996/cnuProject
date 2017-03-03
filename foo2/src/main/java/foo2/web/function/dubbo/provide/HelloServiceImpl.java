package foo2.web.function.dubbo.provide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.tarzan.zzDemoAPI.HelloService;

import com.alibaba.dubbo.config.annotation.Service;

import foo2.web.function.service.CityService;

@Component
@Service(interfaceClass = org.tarzan.zzDemoAPI.HelloService.class)
public class HelloServiceImpl implements HelloService {

	@Autowired
	@Lazy
	private CityService cityService;

	@Override
	public String setHello() {
		return cityService.getCityName();
	}

}
