package web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goodsCategory")
public class GoodsCategoryController {

	Logger logger = LoggerFactory.getLogger(GoodsCategoryController.class);
	private final static String JSP_PATH = "goodsCategory/";

}
