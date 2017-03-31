package web.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GoodsDto implements Serializable {
	private String id;
	private String name;
	private String exchangeGroupId;
	private String goodsCategoryCode;
	private String goodsCategorySubCode;
	private String goodscol;
	private int userId;
	private String goodsCategoryName;
	private String goodsCategorySubName;
	private int state;
	private String url;

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

	public String getGoodscol() {
		return goodscol;
	}

	public void setGoodscol(String goodscol) {
		this.goodscol = goodscol;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
