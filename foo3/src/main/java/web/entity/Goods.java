package web.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_goods")
public class Goods implements Serializable {
	public final static int DEAL = 1;
	public final static int GROUNDING = 1;
	public final static int NOT_GROUNDING = 0;
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
	@Column(name = "user_id")
	private int userId;

	@Column(name = "title_url")
	private String titleUrl;

	@Transient
	private String goodsCategoryName;
	@Transient
	private String goodsCategorySubName;

	private int state;
	
	private int deal;

	private int adminGrounding;

	@Column(name = "send_date")
	private Timestamp sendDate;

	@Column(name = "exchange_address")
	private String exchangeAddress;

	@Column(name = "goods_desc")
	private String goodsDesc;

	@Transient
	private int daysBetween;

	@Transient
	private List<GoodsPic> goodsPicList = new ArrayList<>();

	@Transient
	private User user;

	@Transient
	private ExchangeOrder eo;

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

	public String getTitleUrl() {
		return titleUrl;
	}

	public void setTitleUrl(String titleUrl) {
		this.titleUrl = titleUrl;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAdminGrounding() {
		return adminGrounding;
	}

	public void setAdminGrounding(int adminGrounding) {
		this.adminGrounding = adminGrounding;
	}

	public Timestamp getSendDate() {
		return sendDate;
	}

	public void setSendDate(Timestamp sendDate) {
		this.sendDate = sendDate;
	}

	public int getDaysBetween() {
		return daysBetween;
	}

	public void setDaysBetween(int daysBetween) {
		this.daysBetween = daysBetween;
	}

	public List<GoodsPic> getGoodsPicList() {
		return goodsPicList;
	}

	public void setGoodsPicList(List<GoodsPic> goodsPicList) {
		this.goodsPicList = goodsPicList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getExchangeAddress() {
		return exchangeAddress;
	}

	public void setExchangeAddress(String exchangeAddress) {
		this.exchangeAddress = exchangeAddress;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public ExchangeOrder getEo() {
		return eo;
	}

	public void setEo(ExchangeOrder eo) {
		this.eo = eo;
	}

	public int getDeal() {
		return deal;
	}

	public void setDeal(int deal) {
		this.deal = deal;
	}

}
