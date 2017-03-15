package foo3;

import java.io.Serializable;
import java.sql.Timestamp;

public class TransAccbaseDto implements Serializable {
	private static final long serialVersionUID = -4572822344065724173L;

	TransAccbaseReq req = new TransAccbaseReq();
	TransAccbaseResp resp = new TransAccbaseResp();

	public TransAccbaseReq getReq() {
		return req;
	}

	public void setReq(TransAccbaseReq req) {
		this.req = req;
	}

	public TransAccbaseResp getResp() {
		return resp;
	}

	public void setResp(TransAccbaseResp resp) {
		this.resp = resp;
	}

	public final static class TransAccbaseReq implements Serializable {
		private String accNo;

		public String getAccNo() {
			return accNo;
		}

		public void setAccNo(String accNo) {
			this.accNo = accNo;
		}
	}

	public final static class TransAccbaseResp implements Serializable {

		private int issue_id;

		private int acctype_id;

		private String acc_no;

		private String acc_pwd;

		private String user_id;

		private String is_pwd;

		private String user_name;

		private String user_idno;

		private String currency;

		private String serial;

		private String batch_no;

		private Timestamp begin_time;

		private Timestamp end_time;

		private int alloted;

		private int state;

		public int getIssue_id() {
			return issue_id;
		}

		public void setIssue_id(int issue_id) {
			this.issue_id = issue_id;
		}

		public int getAcctype_id() {
			return acctype_id;
		}

		public void setAcctype_id(int acctype_id) {
			this.acctype_id = acctype_id;
		}

		public String getAcc_no() {
			return acc_no;
		}

		public void setAcc_no(String acc_no) {
			this.acc_no = acc_no;
		}

		public String getAcc_pwd() {
			return acc_pwd;
		}

		public void setAcc_pwd(String acc_pwd) {
			this.acc_pwd = acc_pwd;
		}

		public String getIs_pwd() {
			return is_pwd;
		}

		public void setIs_pwd(String is_pwd) {
			this.is_pwd = is_pwd;
		}

		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}

		public String getUser_idno() {
			return user_idno;
		}

		public void setUser_idno(String user_idno) {
			this.user_idno = user_idno;
		}

		public String getBatch_no() {
			return batch_no;
		}

		public void setBatch_no(String batch_no) {
			this.batch_no = batch_no;
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public String getSerial() {
			return serial;
		}

		public void setSerial(String serial) {
			this.serial = serial;
		}

		public Timestamp getBegin_time() {
			return begin_time;
		}

		public void setBegin_time(Timestamp begin_time) {
			this.begin_time = begin_time;
		}

		public Timestamp getEnd_time() {
			return end_time;
		}

		public void setEnd_time(Timestamp end_time) {
			this.end_time = end_time;
		}

		public int getAlloted() {
			return alloted;
		}

		public void setAlloted(int alloted) {
			this.alloted = alloted;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}
	}
}