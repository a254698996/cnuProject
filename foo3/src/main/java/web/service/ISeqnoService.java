package web.service;

import java.io.Serializable;

public interface ISeqnoService<T, PK extends Serializable> extends IService<T, PK> {
	public int getSeqno() throws Exception;
}
