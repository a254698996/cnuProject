package company;

import java.util.HashSet;
import java.util.Set;

public class CompanyInfo {

	private String compnayNo;

	private String companyName;

	private CompanyState companyState;

	private Set<Emp> empSet = new HashSet<Emp>();
	private Set<Emp> empManageSet = new HashSet<Emp>();
	private Set<EmpOffer> empOfferSet = new HashSet<EmpOffer>();

	private Account companyAccount;

	@Override
	public String toString() {
		return "CompanyInfo [compnayNo=" + compnayNo + ", companyName=" + companyName + ", companyState=" + companyState
				+ ", empSet=" + empSet + ", empManageSet=" + empManageSet + ", empOfferSet=" + empOfferSet + "]";
	}

	public void open() {
		this.companyState = CompanyState.open;
	}

	public void close() {
		this.companyState = CompanyState.close;
	}

	public void addOneManager(Emp emp) {
		this.empManageSet.add(emp);
	}

	public void addOneEmp(Emp emp) {
		this.empSet.add(emp);
	}

	public void addEmpOffer(EmpOffer empOffer) {
		this.empOfferSet.add(empOffer);
	}

	public String getCompnayNo() {
		return compnayNo;
	}

	public void setCompnayNo(String compnayNo) {
		this.compnayNo = compnayNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Set<Emp> getEmpSet() {
		return empSet;
	}

	public void setEmpSet(Set<Emp> empSet) {
		this.empSet = empSet;
	}

	public Set<Emp> getEmpManageSet() {
		return empManageSet;
	}

	public void setEmpManageSet(Set<Emp> empManageSet) {
		this.empManageSet = empManageSet;
	}

	public CompanyState getCompanyState() {
		return companyState;
	}

	public void setCompanyState(CompanyState companyState) {
		this.companyState = companyState;
	}

	public Set<EmpOffer> getEmpOfferSet() {
		return empOfferSet;
	}

	public void setEmpOfferSet(Set<EmpOffer> empOfferSet) {
		this.empOfferSet = empOfferSet;
	}

	public Account getCompanyAccount() {
		return companyAccount;
	}

	public void setCompanyAccount(Account companyAccount) {
		this.companyAccount = companyAccount;
	}

}
