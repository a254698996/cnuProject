package company;

import java.util.Set;

public class Company {

	private CompanyInfo companyInfo;
	private Set<SalaryRule> empSalaryRuleSet;

	public Company(CompanyInfo companyInfo, Set<SalaryRule> empSalaryRuleSet) {
		this.companyInfo = companyInfo;
		this.empSalaryRuleSet = empSalaryRuleSet;
	}

	public void balanceEmpSalary() {

	}

//	private double calcEmpSalarySum() {
//		double empSalarySum = 0.0;
//		Set<EmpOffer> empOfferSet = this.companyInfo.getEmpOfferSet();
//	 
//	}
	
//	private void 

	// private boolean canBalanceEmpSalary(){
	// this.companyInfo.getCompanyAccount().getBalance()>0?
	// }

	public void business() {
		System.out.println(" company business ...... ");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void open() {
		this.companyInfo.open();
	}

	// 招人
	public void recruit(Emp emp, double money) throws Exception {
		// emp exist
		boolean empExist = this.companyInfo.getEmpSet().contains(emp);
		if (empExist) {
			throw new Exception(emp.toString() + " 应该员工已在职");
		}

		// set emp level
		setEmpLevel(emp);

		if (emp.getEmpLevel().equals(EmpLevel.manger)) {
			this.companyInfo.addOneManager(emp);
		}

		if (emp.getEmpLevel().equals(EmpLevel.normal)) {
			this.companyInfo.addOneEmp(emp);
		}

		EmpOffer empOffer = getEmpOffer(emp, money);

		this.companyInfo.addEmpOffer(empOffer);
	}

	public String queryCompanyInfo() {
		return this.companyInfo.toString();
	}

	private EmpOffer getEmpOffer(Emp emp, double money) {
		EmpLevel empLevel = emp.getEmpLevel();
		SalaryRule empSalaryRule = getEmpSalaryRule(empLevel);
		return new EmpOffer(this, emp, empSalaryRule, money);
	}

	private SalaryRule getEmpSalaryRule(EmpLevel empLevel) {
		for (SalaryRule esr : this.empSalaryRuleSet) {
			if (esr.getEmpLevel().equals(empLevel)) {
				return esr;
			}
		}
		return null;
	}

	private void setEmpLevel(Emp emp) {
		EmpLevel empLevel = caleEmpLevel(emp.getWorkforceCapability());
		emp.setEmpLevel(empLevel);
	}

	private EmpLevel caleEmpLevel(int workforceCapability) {
		if (workforceCapability > 80) {
			return EmpLevel.manger;
		}
		return EmpLevel.normal;
	}
	// 制员工级别 级薪资

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	public Set<SalaryRule> getEmpSalaryRuleSet() {
		return empSalaryRuleSet;
	}

	public void setEmpSalaryRuleSet(Set<SalaryRule> empSalaryRuleSet) {
		this.empSalaryRuleSet = empSalaryRuleSet;
	}

}
