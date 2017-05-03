package web.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hibernate.dao.base.Page;

import web.entity.GoodsCategory;
import web.service.IGoodsCategoryService;

@Controller
@RequestMapping("/goodsCategory")
public class GoodsCategoryController {

	Logger logger = LoggerFactory.getLogger(GoodsCategoryController.class);
	private final static String JSP_PATH = "goodsCategory/";

	@Autowired
	@Lazy
	IGoodsCategoryService<GoodsCategory, Serializable> goodsCategoryService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(GoodsCategory goodsCategory, @RequestParam(required = false) Integer pageIndex) {
		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}
		Page page = goodsCategoryService.pagedQuery("from GoodsCategory g where g.pcode is null ", pageIndex, Page.defaultPageSize);
		ModelAndView mav = new ModelAndView(getPath("goodsCategoryList"));
		mav.getModelMap().put("goodsCategoryList", page.getList());
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		return mav;
	}

	@RequestMapping(value = "toAdd", method = RequestMethod.GET)
	public String toAdd(GoodsCategory goodsCategory) {
		return getPath("addGoodsCategory");
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ModelAndView add(GoodsCategory goodsCategory) {
		goodsCategoryService.save(goodsCategory);
		return new ModelAndView("redirect:/goodsCategory/list");
	}

	@RequestMapping(value = "toUpdate/{id}", method = RequestMethod.GET)
	public ModelAndView toUpdate(@PathVariable("id") Integer id) {
		GoodsCategory goodsCategory2 = goodsCategoryService.get(id);
		ModelAndView mav = new ModelAndView(getPath("updateGoodsCategory"));
		mav.getModel().put("goodsCategory", goodsCategory2);
		return mav;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(GoodsCategory goodsCategory) {
		goodsCategoryService.update(goodsCategory);
		return new ModelAndView("redirect:/goodsCategory/list");
	}

	@RequestMapping(value = "toSubList/{pcode}", method = RequestMethod.GET)
	public ModelAndView toSubList(@PathVariable("pcode") String pcode,
			@RequestParam(required = false) Integer pageIndex) {
		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}

		Page page = goodsCategoryService.pagedQuery("from GoodsCategory g where g.pcode=?", pageIndex, Page.defaultPageSize, new Object[]{pcode});

		ModelAndView mav = new ModelAndView(getPath("subList"));
		mav.getModel().put("subList", page.getList());
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		return mav;
	}

	@RequestMapping(value = "toAddSub/{pcode}", method = RequestMethod.GET)
	public ModelAndView toAddSub(@PathVariable("pcode") String pcode) {
		ModelAndView mav = new ModelAndView(getPath("addSub"));
		mav.getModel().put("pcode", pcode);
		return mav;
	}

	@RequestMapping(value = "addSub", method = RequestMethod.POST)
	public ModelAndView addSub(GoodsCategory goodsCategory) {
		goodsCategoryService.save(goodsCategory);
		return new ModelAndView("redirect:/goodsCategory/toSubList/"+goodsCategory.getPcode());
	}

	@RequestMapping(value = "toSubUpdate/{id}", method = RequestMethod.GET)
	public ModelAndView toSubUpdate(@PathVariable("id") Integer id) {
		GoodsCategory goodsCategory2 = goodsCategoryService.get(id);
		ModelAndView mav = new ModelAndView(getPath("updateSub"));
		mav.getModel().put("goodsCategory", goodsCategory2);
		return mav;
	}

	@RequestMapping(value = "subUpdate", method = RequestMethod.POST)
	public ModelAndView subUpdate(GoodsCategory goodsCategory) {
		goodsCategoryService.update(goodsCategory);
		return new ModelAndView("redirect:/goodsCategory/toSubList/"+goodsCategory.getPcode());
	}
	
	private String getPath(String path) {
		return JSP_PATH + path;
	}

}
