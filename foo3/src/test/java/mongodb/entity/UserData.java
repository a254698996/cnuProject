package mongodb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import util.JsonUtils2;

public class UserData implements Serializable {

	private static final long serialVersionUID = -4770493237851400594L;

	private String userName;
	private String password;
	private Set<String> pets;
	private Map<String, String> favoriteMovies;

	@JsonFormat(pattern = "yyyy-MM-ddHH:mm:ss")
	private Date birthday;

	public Map toMap() {
		try {
			String obj2json = JsonUtils2.obj2json(this);
			return JsonUtils2.json2map(obj2json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toString() {
		return "UserData [userName=" + userName + ", password=" + password + ", pets=" + pets + ", favoriteMovies="
				+ favoriteMovies + ", birthday=" + birthday + "]";
	}

	public UserData() {
	}

	public UserData(String userName, String passWord) {
		this.userName = userName;
		this.password = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getPets() {
		return pets;
	}

	public void setPets(Set<String> pets) {
		this.pets = pets;
	}

	public Map<String, String> getFavoriteMovies() {
		return favoriteMovies;
	}

	public void setFavoriteMovies(Map<String, String> favoriteMovies) {
		this.favoriteMovies = favoriteMovies;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}