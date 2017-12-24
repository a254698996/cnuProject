package qingfeng;

import java.sql.Timestamp;

public class TransPreCardInfo {
	private String cardid;
	private String merchno;
	private Timestamp begindate;
	private Timestamp enddate;
	private String status;
	private String qcpassed = "1";
	private String rank = "1";
	private String inbatchno;
	private String outbatchno;
	private String isspecial;
	private String salesman;
	private String scardid;
	private String ismoney;
	private String printflag = "0";
	private String serial;
	private String type = "1";
	private String pwd;
	private String pwd_value;
	private String deposit;
	private String isnet = "0";
	private String cardmode;
	private String currency;
	private String optionstatus = "2";
	private Timestamp createdate;
	private Timestamp updatedate;
	private String secondTrack;

	public TransPreCardInfo() {

	}

	public TransPreCardInfo(String cardid) {
		this.cardid = cardid;
	}

	public String toErrorStr() {
		return this.cardid + "," + this.pwd_value + "," + this.secondTrack + "\n";
	}

	@Override
	public String toString() {
		return "TransPreCardInfo [cardid=" + cardid + ", merchno=" + merchno + ", begindate=" + begindate + ", enddate="
				+ enddate + ", status=" + status + ", qcpassed=" + qcpassed + ", rank=" + rank + ", inbatchno="
				+ inbatchno + ", outbatchno=" + outbatchno + ", isspecial=" + isspecial + ", salesman=" + salesman
				+ ", scardid=" + scardid + ", ismoney=" + ismoney + ", printflag=" + printflag + ", serial=" + serial
				+ ", type=" + type + ", pwd=" + pwd + ", pwd_value=" + pwd_value + ", deposit=" + deposit + ", isnet="
				+ isnet + ", cardmode=" + cardmode + ", currency=" + currency + ", optionstatus=" + optionstatus
				+ ", createdate=" + createdate + ", updatedate=" + updatedate + "]";
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getMerchno() {
		return merchno;
	}

	public void setMerchno(String merchno) {
		this.merchno = merchno;
	}

	public Timestamp getBegindate() {
		return begindate;
	}

	public void setBegindate(Timestamp begindate) {
		this.begindate = begindate;
	}

	public Timestamp getEnddate() {
		return enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQcpassed() {
		return qcpassed;
	}

	public void setQcpassed(String qcpassed) {
		this.qcpassed = qcpassed;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getInbatchno() {
		return inbatchno;
	}

	public void setInbatchno(String inbatchno) {
		this.inbatchno = inbatchno;
	}

	public String getOutbatchno() {
		return outbatchno;
	}

	public void setOutbatchno(String outbatchno) {
		this.outbatchno = outbatchno;
	}

	public String getIsspecial() {
		return isspecial;
	}

	public void setIsspecial(String isspecial) {
		this.isspecial = isspecial;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getScardid() {
		return scardid;
	}

	public void setScardid(String scardid) {
		this.scardid = scardid;
	}

	public String getIsmoney() {
		return ismoney;
	}

	public void setIsmoney(String ismoney) {
		this.ismoney = ismoney;
	}

	public String getPrintflag() {
		return printflag;
	}

	public void setPrintflag(String printflag) {
		this.printflag = printflag;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwd_value() {
		return pwd_value;
	}

	public void setPwd_value(String pwd_value) {
		this.pwd_value = pwd_value;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getIsnet() {
		return isnet;
	}

	public void setIsnet(String isnet) {
		this.isnet = isnet;
	}

	public String getCardmode() {
		return cardmode;
	}

	public void setCardmode(String cardmode) {
		this.cardmode = cardmode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getOptionstatus() {
		return optionstatus;
	}

	public void setOptionstatus(String optionstatus) {
		this.optionstatus = optionstatus;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Timestamp getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}

	public String getSecondTrack() {
		return secondTrack;
	}

	public void setSecondTrack(String secondTrack) {
		this.secondTrack = secondTrack;
	}

}
