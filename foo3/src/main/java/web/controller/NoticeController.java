package web.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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

import web.conf.SysInit;
import web.content.Constant;
import web.entity.NoticeActivity;
import web.service.INoticeActivityService;
import web.util.DateFormatUtil;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private final static String JSP_PATH = "notice/";

	@Autowired
	@Lazy
	INoticeActivityService<NoticeActivity, Serializable> noticeActivityService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(NoticeActivity notice,String sendDateParam, @RequestParam(required = false) Integer pageIndex) {
		if (pageIndex == null) {
			pageIndex = Page.defaultStartIndex;
		}
		String hql = "from NoticeActivity u where 1=1  ";

		List<Object> list = new ArrayList<Object>();

		 if (StringUtils.isNotBlank(notice.getName())) {
		 hql += "and u.name like ? ";
		 list.add("%" + notice.getName() + "%");
		 }
		
		 if (StringUtils.isNotBlank(notice.getConent())) {
		 hql += "and u.conent like ? ";
		 list.add("%" + notice.getConent() + "%");
		 }
		
		 if (sendDateParam!=null) {
			 try {
				 Date parse = DateFormatUtil.getDateFormat(DateFormatUtil.DEFAULT_DATE_FORMAT).parse(sendDateParam);
				 list.add(parse);
				 hql += "and  ? between u.sendDate  and u.endDate ";
			} catch (ParseException e) {
			}
		 }

		Page page = noticeActivityService.pagedQuery(hql, pageIndex, Page.defaultPageSize, list.toArray());

		ModelAndView mav = new ModelAndView(getPath("noticeList"));
		mav.getModelMap().put("noticeList", page.getList());
		mav.getModel().put("steps", page.getPageSize());
		mav.getModel().put("pageIndex", pageIndex);
		mav.getModel().put("count", page.getTotalCount());
		return mav;
	}

	@RequestMapping(value = "toAdd", method = RequestMethod.GET)
	public String toAddGoods() {
		return getPath("addNotice");
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ModelAndView addGoods(NoticeActivity na) {
		noticeActivityService.save(na);
		return new ModelAndView("redirect:/notice/list");
	}

	@RequestMapping(value = "changeState/{id}", method = RequestMethod.GET)
	public ModelAndView changeState(@PathVariable int id) {
		NoticeActivity noticeActivity = noticeActivityService.get(id);
		if (noticeActivity.getState() == Constant.State.STATE_NORMAL) {
			noticeActivity.setState(Constant.State.STATE_NOT_NORMAL);
		} else {
			noticeActivity.setState(Constant.State.STATE_NORMAL);
		}
		noticeActivityService.update(noticeActivity);
		if (noticeActivity.getType() == NoticeActivity.NOTICE) {
			SysInit.noticeList = noticeActivityService.getIndexNoticeList(3);
		}else if (noticeActivity.getType() == NoticeActivity.ACTIVITY) {
			SysInit.activityList = noticeActivityService.getIndexActivityList(4);
		}
		return new ModelAndView("redirect:/notice/list");
	}

	private String getPath(String path) {
		return JSP_PATH + path;
	}

}
