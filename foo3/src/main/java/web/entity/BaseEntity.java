package web.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
// @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@MappedSuperclass
public class BaseEntity implements Serializable {
	public final static int STATE_NORMAL = 1;
	public final static int STATE_NOT_NORMAL = 0;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
