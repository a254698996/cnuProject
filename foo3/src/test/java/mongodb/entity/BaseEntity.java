package mongodb.entity;

import java.io.Serializable;

import util.JsonUtils2;

public class BaseEntity implements Serializable, Cloneable {

	private static final long serialVersionUID = -1938461681990103102L;

	@Override
	public String toString() {
		return JsonUtils2.obj2json(this);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
