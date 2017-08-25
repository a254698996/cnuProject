package company;

public class Emp {

	private String empNo;

	private String empName;

	private String companyNo;

	private EmpLevel empLevel;

	private EmpState empState;

	private EmpMood empMood;

	private EmpOffer empOffer;

	private int workforceCapability;

	private Account empAccount;

	// 辞职
	public void resigned() {
		this.companyNo = null;
		this.empOffer = null;
		this.empState = EmpState.dimission;
	}

	@Override
	public String toString() {
		return "Emp [empNo=" + empNo + ", empName=" + empName + ", companyNo=" + companyNo + ", empLevel=" + empLevel
				+ ", empState=" + empState + ", empOffer=" + empOffer + ", workforceCapability=" + workforceCapability
				+ ", empAccount=" + empAccount + "]";
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public EmpLevel getEmpLevel() {
		return empLevel;
	}

	public void setEmpLevel(EmpLevel empLevel) {
		this.empLevel = empLevel;
	}

	public EmpState getEmpState() {
		return empState;
	}

	public void setEmpState(EmpState empState) {
		this.empState = empState;
	}

	public EmpOffer getEmpOffer() {
		return empOffer;
	}

	public void setEmpOffer(EmpOffer empOffer) {
		this.empOffer = empOffer;
	}

	public int getWorkforceCapability() {
		return workforceCapability;
	}

	public void setWorkforceCapability(int workforceCapability) {
		this.workforceCapability = workforceCapability;
	}

	public Account getEmpAccount() {
		return empAccount;
	}

	public void setEmpAccount(Account empAccount) {
		this.empAccount = empAccount;
	}

	public EmpMood getEmpMood() {
		return empMood;
	}

	public void setEmpMood(EmpMood empMood) {
		this.empMood = empMood;
	}

}
