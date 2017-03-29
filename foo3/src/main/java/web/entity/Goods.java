package web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_goods")
public class Goods implements Serializable {

	@Id
	private String id;

	private String name;
	@Column(name = "t_exchange_group_id")
	private String exchangeGroupId;
	@Column(name = "t_goods_category_code")
	private String goodsCategoryCode;
	@Column(name = "t_goods_category_sub_code")
	private String goodsCategorySubCode;
	@Column(name = "t_goodscol")
	private String goodscol;

	@Transient
	private String goodsCategoryName;
	@Transient
	private String goodsCategorySubName;

	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getGoodscol() {
		return goodscol;
	}

	public void setGoodscol(String goodscol) {
		this.goodscol = goodscol;
	}

	public String getGoodsCategoryCode() {
		return goodsCategoryCode;
	}

	public void setGoodsCategoryCode(String goodsCategoryCode) {
		this.goodsCategoryCode = goodsCategoryCode;
	}

	public String getGoodsCategorySubCode() {
		return goodsCategorySubCode;
	}

	public void setGoodsCategorySubCode(String goodsCategorySubCode) {
		this.goodsCategorySubCode = goodsCategorySubCode;
	}

	public String getGoodsCategoryName() {
		return goodsCategoryName;
	}

	public void setGoodsCategoryName(String goodsCategoryName) {
		this.goodsCategoryName = goodsCategoryName;
	}

	public String getGoodsCategorySubName() {
		return goodsCategorySubName;
	}

	public void setGoodsCategorySubName(String goodsCategorySubName) {
		this.goodsCategorySubName = goodsCategorySubName;
	}

}
