package company;

import java.io.Serializable;

import util.JSONUtils;

public class BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return JSONUtils.obj2json(this);
	}

}
