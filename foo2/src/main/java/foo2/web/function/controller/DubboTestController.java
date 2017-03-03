package foo2.web.function.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import foo2.web.function.dubbo.consumer.DubboClient;

@Controller
public class DubboTestController {
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	@Lazy
	private DubboClient dubboClient;

	@RequestMapping("/dubbo")
	@ResponseBody
	public String index() {
		dubboClient.sendMessage("是不是这样的夜晚你才会这样的想起我" + sdf.format(new Date()));
		return String.valueOf(sdf.format(new Date()));
	}

}
