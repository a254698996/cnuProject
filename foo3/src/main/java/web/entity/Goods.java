package web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_goods")
public class Goods extends BaseEntity {

	private String name;
	@Column(name = "t_exchange_group_id")
	private String exchangeGroupId;
	@Column(name = "t_goods_category_id")
	private String goodsCategoryId;
	@Column(name = "t_goodscol")
	private String goodscol;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExchangeGroupId() {
		return exchangeGroupId;
	}

	public void setExchangeGroupId(String exchangeGroupId) {
		this.exchangeGroupId = exchangeGroupId;
	}

	public String getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(String goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public String getGoodscol() {
		return goodscol;
	}

	public void setGoodscol(String goodscol) {
		this.goodscol = goodscol;
	}

}
