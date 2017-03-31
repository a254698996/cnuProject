package web.controller;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
		Page page = goodsService.pagedQuery(
				"select g ,c ,subC from Goods g, GoodsCategory c, GoodsCategory subC where g.goodsCategoryCode=c.code and g.goodsCategorySubCode=subC.code ",
				pageIndex, Page.defaultPageSize);
		List<Object> list = page.getList();
		List<Goods> goodsList = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			for (Object object : list) {
				Object[] objs = (Object[]) object;
				Goods goods = (web.entity.Goods) objs[0];
				GoodsCategory c = (web.entity.GoodsCategory) objs[1];
				GoodsCategory subC = (web.entity.GoodsCategory) objs[2];
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
	public ResponseEntity<GoodsPic> addGoodsPic(@RequestParam(value = "files", required = false) MultipartFile file,
			HttpServletRequest request) {
		if (file.isEmpty()) {
			logger.info("file is empty ");
			GoodsPic goodsPic = new GoodsPic();
			goodsPic.setState("0");
			return new ResponseEntity<GoodsPic>(goodsPic, HttpStatus.OK);
		}

		String path = request.getSession().getServletContext().getRealPath("upload");
		String originalFilename = file.getOriginalFilename();
		String fileName = new Date().getTime() + ".jpg";
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		String porjectPath = getPorjectPath();
		logger.info("porjectPath   " + porjectPath);

		// 保存
		long size = 0;
		try {
			size = file.getSize();
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		GoodsPic goodsPic = new GoodsPic();
		goodsPic.setName(originalFilename);
		goodsPic.setSize(size);
		goodsPic.setUrl(fileName);
		goodsPic.setState("1");
		goodsPicService.save(goodsPic);
		return new ResponseEntity<GoodsPic>(goodsPic, HttpStatus.OK);
	}

	@RequestMapping(value = "deleteGoodsPic/{goodsPicId}", method = RequestMethod.POST)
	public ResponseEntity<String> deleteGoodsPic(@PathVariable Integer goodsPicId, String picUrl,
			HttpServletRequest request) {
		goodsPicService.removeById(goodsPicId);
		String path = request.getSession().getServletContext().getRealPath("upload") + File.separator + picUrl;
		delete(path);
		logger.info("path  " + path);
		return new ResponseEntity<String>("hehe", HttpStatus.OK);
	}

	@RequestMapping(value = "addGoods", method = RequestMethod.POST)
	public ModelAndView addGoods(Goods goods, Integer[] goodsPicIds) {
		String goodsId = UUID.randomUUID().toString();
		goods.setId(goodsId);
		goodsService.save(goods);
		for (Integer goodsPicId : goodsPicIds) {
			GoodsPic goodsPic = goodsPicService.get(goodsPicId);
			goodsPic.setGoodsId(goodsId);
			goodsPicService.update(goodsPic);
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

		List<?> goodsList = goodsService.find(
				"select g ,c ,subC from Goods g, GoodsCategory c, GoodsCategory subC where g.goodsCategoryCode=c.code and g.goodsCategorySubCode=subC.code and g.id=? ",
				new Object[] { id });

		Goods goods = null;
		if (goodsList != null && !goodsList.isEmpty()) {
			Object[] objs = (Object[]) goodsList.get(0);
			goods = (web.entity.Goods) objs[0];
			GoodsCategory c = (web.entity.GoodsCategory) objs[1];
			GoodsCategory subC = (web.entity.GoodsCategory) objs[2];
			goods.setGoodsCategoryName(c.getName());
			goods.setGoodsCategorySubName(subC.getName());
		}

		List<GoodsPic> picList = goodsPicService.findBy("goodsId", id);

		ModelAndView modelAndView = new ModelAndView(getPath("detailGoods"));
		modelAndView.addObject("goods", goods);
		modelAndView.addObject("picList", picList);
		return modelAndView;
	}

	private void delete(String fileUrl) {
		File file = new File(fileUrl);
		file.delete();
	}

	private String getPath(String path) {
		return JSP_PATH + path;
	}

	private String getPorjectPath() {
		String nowpath; // 当前tomcat的bin目录的路径 如
						// D:/java/software/apache-tomcat-6.0.14/bin
		String tempdir;
		nowpath = System.getProperty("user.dir");
		tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
		// tempdir+="//foo3";
		// //拼成D:/java/software/apache-tomcat-6.0.14/webapps/sz_pro
		return tempdir;
	}
}
