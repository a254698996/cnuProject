package web.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_exchange_order")
public class ExchangeOrder extends BaseEntity {

	public static final int SUCCESS = 1;
	public static final int CLOSE = 9;
	@Column(name = "t_goods_id")
	private String goodsId;
	@Column(name = "t_exchange_goods_id")
	private String exchangeGoodsId;
	@Column(name = "success_date")
	private Timestamp successDate;
	@Column(name = "success_flag")
	private int successFlag;
	@Column(name = "exchange_state")
	private int exchangeState;
	@Column(name = "user_id")
	private int userId;
	@Column(name = "exchange_user_id")
	private int exchangeUserId;

	@Transient
	private User user;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getExchangeGoodsId() {
		return exchangeGoodsId;
	}

	public void setExchangeGoodsId(String exchangeGoodsId) {
		this.exchangeGoodsId = exchangeGoodsId;
	}

	public Timestamp getSuccessDate() {
		return successDate;
	}

	public void setSuccessDate(Timestamp successDate) {
		this.successDate = successDate;
	}

	public int getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(int successFlag) {
		this.successFlag = successFlag;
	}

	public int getExchangeState() {
		return exchangeState;
	}

	public void setExchangeState(int exchangeState) {
		this.exchangeState = exchangeState;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getExchangeUserId() {
		return exchangeUserId;
	}

	public void setExchangeUserId(int exchangeUserId) {
		this.exchangeUserId = exchangeUserId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
