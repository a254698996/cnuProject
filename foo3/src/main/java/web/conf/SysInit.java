package web.conf;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.hibernate.dao.base.Page;

import web.entity.Goods;
import web.entity.GoodsCategory;
import web.entity.GoodsPic;
import web.entity.NoticeActivity;
import web.service.IGoodsCategoryService;
import web.service.IGoodsPicService;
import web.service.IGoodsService;
import web.service.INoticeActivityService;

@Component
public class SysInit implements InitializingBean {

	Logger logger = LoggerFactory.getLogger(SysInit.class);

	@Autowired
	@Lazy
	IGoodsCategoryService<GoodsCategory, Serializable> goodsCategoryService;

	@Autowired
	@Lazy
	INoticeActivityService<NoticeActivity, Serializable> noticeActivityService;

	@Autowired
	@Lazy
	IGoodsService<Goods, Serializable> goodsService;

	@Autowired
	@Lazy
	IGoodsPicService<GoodsPic, Serializable> goodsPicService;

	public static List<Object> goodsCategoryList;
	public static List<Object> noticeList;
	public static List<Object> activityList;
	public static List<Object> goodsList;

	@Override
	public void afterPropertiesSet() throws Exception {
		Page page = goodsService.getList(Page.defaultStartIndex, 16, null, null, null, Goods.GROUNDING);
		SysInit.goodsList = page.getList();
		page = goodsCategoryService.pagedQuery("from GoodsCategory g where g.pcode is null ", Page.defaultStartIndex,
				7);
		SysInit.goodsCategoryList = page.getList();
		SysInit.noticeList = noticeActivityService.getIndexNoticeList(3);
		SysInit.activityList = noticeActivityService.getIndexActivityList(4);
	}
}
