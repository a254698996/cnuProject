package web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

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

import web.entity.ExchangeGroup;
import web.entity.Goods;
import web.service.IExchangeGroupService;
import web.service.IGoodsService;

@Controller
@RequestMapping("/exchangeGroup")
public class ExchangeGroupController {

	Logger logger = LoggerFactory.getLogger(ExchangeGroupController.class);
	private final static String JSP_PATH = "exchangeGroup/";

	@Autowired
	@Lazy
	IExchangeGroupService<ExchangeGroup, Serializable> exchangeGroupService;

	@Autowired
	@Lazy
	IGoodsService<Goods, Serializable> goodsService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(ExchangeGroup exchangeGroup, @RequestParam(required = false) Integer pageIndex,
			HttpServletRequest request) {
		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}

		// User currUser = (User) SessionUtil.getAttribute(request,
		// User.SESSION_USER);

		// Page page = goodsService.getList(pageIndex, Page.defaultPageSize,
		// currUser.getId()+"", _SCH_name);

		Page page = exchangeGroupService.pagedQuery("from ExchangeGroup g  order by g.updatetime desc ", pageIndex,
				Page.defaultPageSize);

		ModelAndView mav = new ModelAndView(getPath("exchangeGroupList"));
		mav.getModelMap().put("exchangeGroupList", page.getList());
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		return mav;
	}

	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public ModelAndView get(@PathVariable int id, Integer userId, HttpServletRequest request) {

		int userIdParam = 0;
		if (userId != null) {
			userIdParam = userId;
		} else {
			// User currUser = (User) SessionUtil.getAttribute(request,
			// User.SESSION_USER);
			// userIdParam = currUser.getId();
		}

		ExchangeGroup exchangeGroup = exchangeGroupService.get(id);

		Page goodsList = goodsService.getList(Page.defaultStartIndex, Integer.MAX_VALUE, userIdParam, null,
				exchangeGroup.getId(),null);

		ModelAndView mav = new ModelAndView(getPath("exchangeGroupList"));
		mav.getModelMap().put("exchangeGroup", exchangeGroup);
		mav.getModelMap().put("goodsList", goodsList);
		return mav;
	}

	@RequestMapping("add")
	public void add(ExchangeGroup e) {
		exchangeGroupService.save(e);
	}

	private String getPath(String path) {
		return JSP_PATH + path;
	}
}
