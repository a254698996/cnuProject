package web.controller;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

import web.conf.SysInit;
import web.dto.GoodsDto;
import web.entity.Goods;
import web.entity.GoodsCategory;
import web.entity.GoodsPic;
import web.entity.User;
import web.service.IGoodsCategoryService;
import web.service.IGoodsPicService;
import web.service.IGoodsService;
import web.util.SessionUtil;

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

	@RequestMapping(value = "showGoods", method = RequestMethod.GET)
	public ModelAndView showGoods(Goods Goods, String _SCH_name, @RequestParam(required = false) Integer pageIndex,HttpServletRequest request) {
		Page page = goodsService.getList(pageIndex, 100, null, _SCH_name,null,null);
		ModelAndView mav = new ModelAndView(getPath("showGoods"));
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		List<Object> list = page.getList();
		
		List<GoodsDto> goodsDtoList = new ArrayList<GoodsDto>();
		if (list != null && !list.isEmpty()) {
			for (Object object : list) {
				Goods goods = (web.entity.Goods) object;
				GoodsDto goodsDto = new GoodsDto();
				BeanUtils.copyProperties(goods, goodsDto);
				Page pagedQuery = goodsPicService.pagedQuery("from GoodsPic p where p.goodsId=? ", 1, 1,
						new Object[] { goods.getId() });
				if (pagedQuery.getList() != null && !pagedQuery.getList().isEmpty()) {
					GoodsPic goodsPic = (GoodsPic) pagedQuery.getList().get(0);
					goodsDto.setUrl(goodsPic.getUrl());
					goodsDtoList.add(goodsDto);
				}
			}
		}
		mav.getModelMap().put("goodsDtoList", goodsDtoList);
		return mav;
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(Goods Goods, String _SCH_name, @RequestParam(required = false) Integer pageIndex,HttpServletRequest request) {
		
//		User currUser = (User) SessionUtil.getAttribute(request, User.SESSION_USER);
 
//		Page page = goodsService.getList(pageIndex, Page.defaultPageSize, currUser.getId()+"", _SCH_name);
		Page page = goodsService.getList(pageIndex, Page.defaultPageSize, null, _SCH_name,null,null);
		 
		ModelAndView mav = new ModelAndView(getPath("goodsList"));
		mav.getModelMap().put("goodsList", page.getList());
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

	@RequestMapping(value = "deleteGoodsPic", method = RequestMethod.POST)
	public ResponseEntity<String> deleteGoodsPic(Integer key, String picUrl, HttpServletRequest request) {
		String path = "";
		if (StringUtils.isNotBlank(picUrl)) {
			path = request.getSession().getServletContext().getRealPath("upload") + File.separator + picUrl;
		} else {
			GoodsPic goodsPic = goodsPicService.get(key);
			path = request.getSession().getServletContext().getRealPath("upload") + File.separator + goodsPic.getUrl();
		}
		goodsPicService.removeById(key);
		delete(path);
		logger.info("path  " + path);
		return new ResponseEntity<String>("hehe", HttpStatus.OK);
	}

	@RequestMapping(value = "addGoods", method = RequestMethod.POST)
	public ModelAndView addGoods(Goods goods, Integer[] goodsPicIds,HttpServletRequest request) {
		String goodsId = UUID.randomUUID().toString();
		
		if(goodsPicIds!=null &&goodsPicIds.length>0){
			for (Integer goodsPicId : goodsPicIds) {
				GoodsPic goodsPic = goodsPicService.get(goodsPicId);
				goodsPic.setGoodsId(goodsId);
				goodsPicService.update(goodsPic);
				if(StringUtils.isBlank(goods.getTitleUrl())){
					goods.setTitleUrl(goodsPic.getUrl());
				}
			}
		}else{
			ModelAndView mav = new ModelAndView(getPath("addGoods"));
			mav.addObject("msg", "至少需要上传一张图片");
			return mav;
		}
		User currUser = (User) SessionUtil.getAttribute(request, User.SESSION_USER);
		goods.setUserId(currUser.getId());
		goods.setId(goodsId);
		goods.setAdminGrounding(Goods.GROUNDING);
		goods.setSendDate(new Timestamp(System.currentTimeMillis()));
		goodsService.save(goods);
		
//		return new ModelAndView("redirect:/goods/list");
		return new ModelAndView("redirect:/user/owner");
	}

	@RequestMapping(value = "grounding/{id}", method = RequestMethod.GET)
	public ModelAndView grounding(@PathVariable String id,HttpServletRequest request) {
		Goods goods = goodsService.get(id);
		User user = (User) SessionUtil.getAttribute(request, User.SESSION_USER );
		user.getRoleSet().contains("admin");
		
		if (goods.getState() == Goods.NOT_GROUNDING) {
			goods.setState(Goods.GROUNDING);
		} else {
			goods.setState(Goods.NOT_GROUNDING);
		}
		if(goods.getAdminGrounding() == Goods.GROUNDING){
			goodsService.update("web.entity.Goods", goods);
			Page page = goodsService.getList(Page.defaultStartIndex, 16, null, null, null,Goods.GROUNDING);
			SysInit.goodsList = page.getList();
		}
		return new ModelAndView("redirect:/user/owner");
	}
	
	@RequestMapping(value = "adminGrounding/{id}", method = RequestMethod.GET)
	public ModelAndView adminGrounding(@PathVariable String id,HttpServletRequest request) {
		Goods goods = goodsService.get(id);
		if (goods.getState() == Goods.NOT_GROUNDING) {
			goods.setState(Goods.GROUNDING);
			goods.setAdminGrounding(Goods.GROUNDING);
		} else {
			goods.setAdminGrounding(Goods.NOT_GROUNDING);
			goods.setState(Goods.NOT_GROUNDING);
		}
		goodsService.update("web.entity.Goods", goods);
		Page page = goodsService.getList(Page.defaultStartIndex, 16, null, null, null,Goods.GROUNDING);
		SysInit.goodsList = page.getList();
		return new ModelAndView("redirect:/goods/list");
	}

	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public ModelAndView get(@PathVariable String id) {

		Goods goods = getGoodsById(id);

		List<GoodsPic> picList = goodsPicService.findBy("goodsId", id);

		ModelAndView modelAndView = new ModelAndView(getPath("detailGoods"));
		modelAndView.addObject("goods", goods);
		modelAndView.addObject("picList", picList);
		return modelAndView;
	}

	@RequestMapping(value = "toUpdate/{id}", method = RequestMethod.GET)
	public ModelAndView toUpdate(@PathVariable String id, HttpServletRequest request) {

		Goods goods = getGoodsById(id);

		List<GoodsPic> picList = goodsPicService.findBy("goodsId", id);

		JSONArray initialPreviewConfigJsonArray = new JSONArray();
		JSONArray urlJsonArray = new JSONArray();

		if (picList != null && !picList.isEmpty()) {
			try {
				for (GoodsPic goodsPic : picList) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("caption", goodsPic.getName());
					jsonObject.put("size", goodsPic.getSize());
					jsonObject.put("width", "120px");
					jsonObject.put("url", request.getContextPath() + "/goods/deleteGoodsPic");
					jsonObject.put("key", goodsPic.getId());
					initialPreviewConfigJsonArray.put(jsonObject);
					urlJsonArray.put(request.getContextPath() + "/upload/" + goodsPic.getUrl());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<Object> pList = goodsCategoryService.getPList();

		List<Object> subList = goodsCategoryService.getSubList(goods.getGoodsCategoryCode());

		ModelAndView mav = new ModelAndView(getPath("updateGoods"));
		mav.addObject("goods", goods);
		mav.addObject("pList", pList);
		mav.addObject("subList", subList);
		mav.addObject("initialPreviewConfigJsonArray", initialPreviewConfigJsonArray);
		mav.addObject("urlJsonArray", urlJsonArray);
		return mav;
	}

	@RequestMapping(value = "updateGoods", method = RequestMethod.POST)
	public ModelAndView updateGoods(Goods goods, Integer[] goodsPicIds) {
		if (goodsPicIds != null && goodsPicIds.length > 0) {
			for (Integer goodsPicId : goodsPicIds) {
				GoodsPic goodsPic = goodsPicService.get(goodsPicId);
				goodsPic.setGoodsId(goods.getId());
				goodsPicService.update(goodsPic);
			}
		}
		List<GoodsPic> goodsPicList = goodsPicService.findBy("goodsId", goods.getId(), "id", true);
		if (goodsPicList != null && !goodsPicList.isEmpty()) {
			goods.setTitleUrl(goodsPicList.get(0).getUrl());
		}
		goods.setSendDate(new Timestamp(System.currentTimeMillis()));
		goodsService.update(goods);
		
//		return new ModelAndView("redirect:/goods/list");
		return new ModelAndView("redirect:/user/owner");
	}

	private Goods getGoodsById(String id) {
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
		return goods;
	}
	
	private void delete(String fileUrl) {
		File file = new File(fileUrl);
		file.delete();
	}

	private String getPath(String path) {
		return JSP_PATH + path;
	}

	private String getPorjectPath() {
		String nowpath; // 当前tomcat的bin目录的路径 如 //
						// D:/java/software/apache-tomcat-6.0.14/bin
		String tempdir;
		nowpath = System.getProperty("user.dir");
		tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
		// tempdir+="//foo3";
		// //拼成D:/java/software/apache-tomcat-6.0.14/webapps/sz_pro
		return tempdir;
	}
}
