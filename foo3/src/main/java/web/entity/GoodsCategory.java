package web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_goods_category")
public class GoodsCategory extends BaseEntity {

	private String code;
	private String name;
	@Column(name = "sub_code")
	private String subCode;
	@Column(name = "is_sub")
	private String isSub;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getIsSub() {
		return isSub;
	}

	public void setIsSub(String isSub) {
		this.isSub = isSub;
	}
}
