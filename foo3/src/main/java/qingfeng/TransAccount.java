package qingfeng;

import java.sql.Timestamp;

public class TransAccount {

	private String subject;
	private String ownerid;
	private double balance;
	private double realbalance;
	private Timestamp expdate;
	private int version;
	private String currency;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getRealbalance() {
		return realbalance;
	}

	public void setRealbalance(double realbalance) {
		this.realbalance = realbalance;
	}

	public Timestamp getExpdate() {
		return expdate;
	}

	public void setExpdate(Timestamp expdate) {
		this.expdate = expdate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
