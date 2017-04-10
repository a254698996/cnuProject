package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.NoticeActivity;
import web.service.INoticeActivityService;

public class NoticeActivityService  extends ServiceImpl<NoticeActivity, Serializable> implements INoticeActivityService<NoticeActivity, Serializable> {

	public NoticeActivityService (Class<NoticeActivity> entityClass, HibernateEntityDao<NoticeActivity, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}
 

}