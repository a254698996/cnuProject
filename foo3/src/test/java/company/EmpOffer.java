package company;

public class EmpOffer {
	private Company company;
	private Emp emp;
	private SalaryRule empSalaryRule;
	private double money;

	public EmpOffer(Company company, Emp emp, SalaryRule empSalaryRule, double money) {
		super();
		this.company = company;
		this.emp = emp;
		this.empSalaryRule = empSalaryRule;
		this.money = money;
	}

	@Override
	public String toString() {
		return "EmpOffer [company=" + company + ", emp=" + emp + ", empSalaryRule=" + empSalaryRule + ", money=" + money
				+ "]";
	}



	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public SalaryRule getEmpSalaryRule() {
		return empSalaryRule;
	}

	public void setEmpSalaryRule(SalaryRule empSalaryRule) {
		this.empSalaryRule = empSalaryRule;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
}
