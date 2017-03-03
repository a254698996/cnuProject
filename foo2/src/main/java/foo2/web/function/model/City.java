package foo2.web.function.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

@Entity
@Table(name = "city")
@Lazy
public class City implements java.io.Serializable {

	private static final long serialVersionUID = 7730353752127863559L;

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "Name")
	private String name;

	@Column(name = "password")
	private String password;

	// @Column(name = "CountryCode")
	// private String countryCode;
	//
	// @Column(name = "District")
	// private String district;
	//
	// @Column(name = "Population")
	// private Integer population;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// public String getCountryCode() {
	// return countryCode;
	// }
	//
	// public void setCountryCode(String countryCode) {
	// this.countryCode = countryCode;
	// }
	//
	// public String getDistrict() {
	// return district;
	// }
	//
	// public void setDistrict(String district) {
	// this.district = district;
	// }
	//
	// public Integer getPopulation() {
	// return population;
	// }
	//
	// public void setPopulation(Integer population) {
	// this.population = population;
	// }

}
