package company;

public class EmpSalaryBillWater {
	private String no;
	private String sendDate;
	private Emp emp;
	private double realSalary;
	private double realbonus;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public double getRealSalary() {
		return realSalary;
	}

	public void setRealSalary(double realSalary) {
		this.realSalary = realSalary;
	}

	public double getRealbonus() {
		return realbonus;
	}

	public void setRealbonus(double realbonus) {
		this.realbonus = realbonus;
	}

}
