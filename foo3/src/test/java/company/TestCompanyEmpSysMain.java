package company;

import java.util.HashSet;
import java.util.Set;

public class TestCompanyEmpSysMain {

	public static void main(String[] args) {
		CompanyInfo companyInfo = new CompanyInfo();
		companyInfo.setCompanyName("EBC");
		companyInfo.setCompnayNo("929238393");

		SalaryRule managerSalaryRule = new SalaryRule();
		managerSalaryRule.setNo("11");
		managerSalaryRule.setEmpLevel(EmpLevel.manger);
		managerSalaryRule.setName("经理的薪资规则");
		managerSalaryRule.setSalaryPercent(70);
		managerSalaryRule.setBonusPercent(30);

		SalaryRule empSalaryRule = new SalaryRule();
		empSalaryRule.setNo("22");
		empSalaryRule.setEmpLevel(EmpLevel.normal);
		empSalaryRule.setName("普通员工的薪资规则");
		empSalaryRule.setSalaryPercent(80);
		empSalaryRule.setBonusPercent(20);

		Set<SalaryRule> salaryRuleSet = new HashSet<SalaryRule>();

		salaryRuleSet.add(managerSalaryRule);
		salaryRuleSet.add(empSalaryRule);

		Company company = new Company(companyInfo, salaryRuleSet);

		company.open();

		Emp zhangsan = new Emp();
		zhangsan.setEmpName("张三");
		zhangsan.setWorkforceCapability(85);

		try {
			company.recruit(zhangsan, 10000.0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Emp lisi = new Emp();
		lisi.setEmpName("李四");
		lisi.setWorkforceCapability(70);

		try {
			company.recruit(lisi, 8000.0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(company.queryCompanyInfo());
		
		company.business();
		
		
		
		
	}
}
