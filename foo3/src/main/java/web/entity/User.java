package web.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_user")
public class User extends BaseEntity {
	private String nickname;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String memo;
	private String sno;
	private String sname;
	private String passwordask;
	private String passwordanswer;
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getPasswordask() {
		return passwordask;
	}

	public void setPasswordask(String passwordask) {
		this.passwordask = passwordask;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPasswordanswer() {
		return passwordanswer;
	}

	public void setPasswordanswer(String passwordanswer) {
		this.passwordanswer = passwordanswer;
	}

}
