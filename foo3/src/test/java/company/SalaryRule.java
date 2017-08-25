package company;

public class SalaryRule {

	private String no;

	private String name;

	private EmpLevel empLevel;

	private int salaryPercent;

	private int bonusPercent;
	
	@Override
	public String toString() {
		return "EmpSalaryRule [no=" + no + ", name=" + name + ", empLevel=" + empLevel + ", salaryPercent="
				+ salaryPercent + ", bonusPercent=" + bonusPercent + "]";
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EmpLevel getEmpLevel() {
		return empLevel;
	}

	public void setEmpLevel(EmpLevel empLevel) {
		this.empLevel = empLevel;
	}

	public int getSalaryPercent() {
		return salaryPercent;
	}

	public void setSalaryPercent(int salaryPercent) {
		this.salaryPercent = salaryPercent;
	}

	public int getBonusPercent() {
		return bonusPercent;
	}

	public void setBonusPercent(int bonusPercent) {
		this.bonusPercent = bonusPercent;
	}

}
