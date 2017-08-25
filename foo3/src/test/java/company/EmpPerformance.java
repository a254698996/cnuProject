package company;

import java.util.Date;

public class EmpPerformance {

	private String no;
	private Date calcDate;
	private Emp emp;
	private String score;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Date getCalcDate() {
		return calcDate;
	}

	public void setCalcDate(Date calcDate) {
		this.calcDate = calcDate;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}
