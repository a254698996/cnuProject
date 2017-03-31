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

import web.entity.Menu;
import web.service.IMenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	Logger logger = LoggerFactory.getLogger(MenuController.class);
	private final static String JSP_PATH = "menu/";

	@Autowired
	@Lazy
	IMenuService<Menu, Serializable> menuService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(Menu menu, @RequestParam(required = false) Integer pageIndex) {
		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}
		Page page = menuService.pagedQuery("from Menu ", pageIndex, Page.defaultPageSize);
		ModelAndView mav = new ModelAndView(getPath("menuList"));
		mav.getModelMap().put("menuList", page.getList());
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		return mav;
	}

	@RequestMapping(value = "toAdd", method = RequestMethod.GET)
	public String toAdd() {
		return getPath("addMenu");
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ModelAndView add(Menu menu) {
		menuService.save(menu);
		return new ModelAndView("redirect:/menu/list");
	}

	@RequestMapping(value = "toUpdate/{id}", method = RequestMethod.GET)
	public ModelAndView toUpdate(@PathVariable int id) {
		Menu menu = menuService.get(id);
		ModelAndView mav = new ModelAndView(getPath("updateMenu"));
		mav.addObject("menu", menu);
		return mav;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(Menu menu) {
		menuService.update(menu);
		return new ModelAndView("redirect:/menu/list");
	}

	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public ModelAndView get(@PathVariable int id) {
		Menu menu = menuService.get(id);
		ModelAndView mav = new ModelAndView("get");
		mav.addObject("menu", menu);
		return mav;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id) {
		menuService.removeById(id);
		return new ModelAndView("redirect:/menu/list");
	}

	@RequestMapping(value = "changeState/{id}", method = RequestMethod.GET)
	public ModelAndView changeState(@PathVariable int id) {
		Menu menu = menuService.get(id);
		if (menu.getState() == Menu.STATE_NORMAL) {
			menu.setState(Menu.STATE_NOT_NORMAL);
		} else {
			menu.setState(Menu.STATE_NORMAL);
		}
		menuService.update(menu);
		return new ModelAndView("redirect:/menu/list");
	}

	private String getPath(String path) {
		return JSP_PATH + path;
	}

}
