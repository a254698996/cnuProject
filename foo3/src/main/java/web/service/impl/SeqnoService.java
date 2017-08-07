package web.service.impl;

import java.io.Serializable;

import com.hibernate.dao.generic.HibernateGenericDao;

import web.dao.hibernate.impl.HibernateEntityDao;
import web.entity.SeqnoEntity;
import web.service.ISeqnoService;

public class SeqnoService extends ServiceImpl<SeqnoEntity, Serializable>
		implements ISeqnoService<SeqnoEntity, Serializable> {

	public SeqnoService(Class<SeqnoEntity> entityClass, HibernateEntityDao<SeqnoEntity, Serializable> hedao,
			HibernateGenericDao hdao) {
		this.entityClass = entityClass;
		this.setHedao(hedao);
		this.hdao = hdao;
	}

	public int getSeqno() throws Exception {
		SeqnoEntity seqnoEntity = new SeqnoEntity();
		SeqnoEntity returnSeqno = this.hedao.queryBeanByHql(seqnoEntity);
		try {
			while (true) {
				returnSeqno.setSeqno(returnSeqno.getSeqno() + 1);
				returnSeqno.setVersion(returnSeqno.getVersion() + 1);
				try {
					this.hedao.update(returnSeqno);
					break;
				} catch (Exception e) {
					e.printStackTrace();
					returnSeqno = this.hedao.queryBeanByHql(seqnoEntity);
				}
			}
			return returnSeqno.getSeqno();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {

		}
	}

}
