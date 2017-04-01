package web.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_exchange_order")
public class ExchangeOrder extends BaseEntity {
	@Column(name = "t_exchange_group_id_main")
	private int exchangeGroupIdMain;
	@Column(name = "t_exchange_group_id")
	private int exchangeGroupId;
	@Column(name = "success_date")
	private Timestamp successDate;
	@Column(name = "main_success_flag")
	private int mainSuccessFlag;
	@Column(name = "success_flag")
	private int successFlag;
	@Column(name = "exchange_state")
	private int exchangeState;

	public int getExchangeGroupIdMain() {
		return exchangeGroupIdMain;
	}

	public void setExchangeGroupIdMain(int exchangeGroupIdMain) {
		this.exchangeGroupIdMain = exchangeGroupIdMain;
	}

	public int getExchangeGroupId() {
		return exchangeGroupId;
	}

	public void setExchangeGroupId(int exchangeGroupId) {
		this.exchangeGroupId = exchangeGroupId;
	}

	public Timestamp getSuccessDate() {
		return successDate;
	}

	public void setSuccessDate(Timestamp successDate) {
		this.successDate = successDate;
	}

	public int getMainSuccessFlag() {
		return mainSuccessFlag;
	}

	public void setMainSuccessFlag(int mainSuccessFlag) {
		this.mainSuccessFlag = mainSuccessFlag;
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

}
