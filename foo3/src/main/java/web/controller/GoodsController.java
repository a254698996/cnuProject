package web.controller;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hibernate.dao.base.Page;

import web.entity.Goods;
import web.entity.GoodsCategory;
import web.service.IGoodsCategoryService;
import web.service.IGoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	Logger logger = LoggerFactory.getLogger(GoodsController.class);
	private final static String JSP_PATH = "goods/";

	@Autowired
	@Lazy
	IGoodsService<Goods, Serializable> goodsService;

	@Autowired
	@Lazy
	IGoodsCategoryService<GoodsCategory, Serializable> goodsCategoryService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(Goods Goods, @RequestParam(required = false) Integer pageIndex) {
		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}
		Page page = goodsService.pagedQuery("from Goods g   ", pageIndex, 1);
		ModelAndView mav = new ModelAndView(getPath("GoodsList"));
		mav.getModelMap().put("goodsList", page.getList());
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		return mav;
	}

	@RequestMapping(value = "toAddGoods", method = RequestMethod.GET)
	public ModelAndView toAddGoods() {
		List<Object> pList = goodsCategoryService.getPList();
		ModelAndView mav = new ModelAndView(getPath("addGoods"));
		mav.addObject("pList", pList);
		return mav;
	}

	@RequestMapping(value = "getSubGoodsCategoryList/{pcode}", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> getSubGoodsCategoryList(@PathVariable String pcode) throws Exception {
		List<Object> subList = goodsCategoryService.getSubList(pcode);
		return new ResponseEntity<List<Object>>(subList, HttpStatus.OK);
	}

	private String getPath(String path) {
		return JSP_PATH + path;
	}
}
