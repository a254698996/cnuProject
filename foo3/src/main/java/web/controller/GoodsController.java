package web.controller;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hibernate.dao.base.Page;

import web.entity.Goods;
import web.entity.GoodsCategory;
import web.entity.GoodsPic;
import web.service.IGoodsCategoryService;
import web.service.IGoodsPicService;
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
	IGoodsPicService<GoodsPic, Serializable> goodsPicService;

	@Autowired
	@Lazy
	IGoodsCategoryService<GoodsCategory, Serializable> goodsCategoryService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(Goods Goods, @RequestParam(required = false) Integer pageIndex) {
		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}
		Page page = goodsService.pagedQuery("select g ,c ,subC from Goods g, GoodsCategory c, GoodsCategory subC where g.goodsCategoryCode=c.code and g.goodsCategorySubCode=subC.code ", pageIndex, Page.defaultPageSize);
		List<Object> list = page.getList();
		List<Goods> goodsList=new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			for (Object object : list) {
				Object[] objs=(Object[]) object;
				Goods goods=(web.entity.Goods) objs[0];
				GoodsCategory c=(web.entity.GoodsCategory) objs[1];
				GoodsCategory subC=(web.entity.GoodsCategory) objs[2];
				goods.setGoodsCategoryName(c.getName());
				goods.setGoodsCategorySubName(subC.getName());
				goodsList.add(goods);
			}
		}
		ModelAndView mav = new ModelAndView(getPath("goodsList"));
		mav.getModelMap().put("goodsList", goodsList);
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		return mav;
	}

	@RequestMapping(value = "getSubGoodsCategoryList/{pcode}", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> getSubGoodsCategoryList(@PathVariable String pcode) throws Exception {
		List<Object> subList = goodsCategoryService.getSubList(pcode);
		return new ResponseEntity<List<Object>>(subList, HttpStatus.OK);
	}

	@RequestMapping(value = "toAddGoods", method = RequestMethod.GET)
	public ModelAndView toAddGoods() {
		List<Object> pList = goodsCategoryService.getPList();
		ModelAndView mav = new ModelAndView(getPath("addGoods"));
		mav.addObject("pList", pList);
		return mav;
	}

	@RequestMapping(value = "addGoodsPic", method = RequestMethod.POST)
	public ResponseEntity<String> addGoodsPic(@RequestParam(value = "files", required = false) MultipartFile file,
			HttpServletRequest request) {
		if (file.isEmpty()) {
			logger.info("file is empty ");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("state", "0");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

		String path = request.getSession().getServletContext().getRealPath("upload");
		// String fileName = file.getOriginalFilename();
		String fileName = new Date().getTime() + ".jpg";
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String filename = request.getContextPath() + "/upload/" + fileName;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("filename", filename);
		jsonObject.put("state", "1");
		return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "addGoods", method = RequestMethod.POST)
	public ModelAndView addGoods(Goods goods, String uploadFiles) {
		String goodsId = UUID.randomUUID().toString();
		goods.setId(goodsId);
		goodsService.save(goods);
		String[] split = uploadFiles.split(",");
		for (String picUrl : split) {
			if (StringUtils.isNotBlank(picUrl)) {
				GoodsPic goodsPic = new GoodsPic();
				goodsPic.setUrl(picUrl);
				goodsPic.setGoodsId(goodsId);
				goodsPicService.save(goodsPic);
			}
		}
		return new ModelAndView("redirect:/goods/list");
	}

	@RequestMapping(value = "grounding/{id}", method = RequestMethod.GET)
	public ModelAndView grounding(@PathVariable String id) {
		Goods goods = goodsService.get(id);
		goods.setState(1);
		goodsService.update("web.entity.Goods", goods);
		return new ModelAndView("redirect:/goods/list");
	}

	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public ModelAndView get(@PathVariable String id) {
		Goods goods = goodsService.get(id);
		goodsService.find(hql, values);
		
		List<GoodsPic> picList = goodsPicService.findBy("goodsId", id);
		
		ModelAndView modelAndView = new ModelAndView(getPath("detailGoods"));
		modelAndView.addObject("goods", goods);
		modelAndView.addObject("picList", picList);
		return modelAndView;
	}

	private String getPath(String path) {
		return JSP_PATH + path;
	}
}
