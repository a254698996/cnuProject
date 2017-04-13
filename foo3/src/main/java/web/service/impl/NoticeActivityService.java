package web.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.hibernate.dao.base.Page;
import com.hibernate.dao.generic.HibernateGenericDao;

import web.conf.SysInit;
import web.content.Constant;
import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.NoticeActivity;
import web.service.INoticeActivityService;

public class NoticeActivityService extends ServiceImpl<NoticeActivity, Serializable>
		implements INoticeActivityService<NoticeActivity, Serializable> {

	public NoticeActivityService(Class<NoticeActivity> entityClass,
			HibernateEntityDao<NoticeActivity, Serializable> hedao, HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Object> getIndexNoticeList(int pageSize) {
		Page page = pagedQuery(
				"from NoticeActivity n where n.type=1 and n.state=? and n.endDate >= ? and n.sendDate <=? ",
				Page.defaultStartIndex, pageSize, new Object[] { Constant.State.STATE_NORMAL, new Date(), new Date() });
		return page.getList();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Object> getIndexActivityList(int pageSize) {
		Page page = pagedQuery(
				"from NoticeActivity n where n.type=2 and n.state=? and n.endDate >= ? and n.sendDate <=? ",
				Page.defaultStartIndex, pageSize, new Object[] { Constant.State.STATE_NORMAL, new Date(), new Date() });
		return page.getList();
	}

}