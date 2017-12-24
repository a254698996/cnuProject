package qingfeng;

public class TransUsers {
	private String personid;
	private String password;
	private String type;
	private String lockdatetime;
	private String pwdexpdate;

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLockdatetime() {
		return lockdatetime;
	}

	public void setLockdatetime(String lockdatetime) {
		this.lockdatetime = lockdatetime;
	}

	public String getPwdexpdate() {
		return pwdexpdate;
	}

	public void setPwdexpdate(String pwdexpdate) {
		this.pwdexpdate = pwdexpdate;
	}

}
