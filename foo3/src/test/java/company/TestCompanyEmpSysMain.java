package company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TestCompanyEmpSysMain {

	public static void main(String[] args) {
		testCompanyEmpSys();
	}

	private void testResult() {
		double a = 1.00;
		double b = 8000.0;

		double c = 0.8;
		double result_a = a * b;
		double result_b = result_a * c;
		System.out.println(result_a);
		System.out.println(result_b);
	}

	private static void testCompanyEmpSys() {

		CompanyInfo companyInfo = new CompanyInfo();
		companyInfo.setCompanyName("EBC");
		companyInfo.setCompnayNo("929238393");

		Account companyAccount = new Account();
		companyAccount.setAccountNumber("company998");
		companyAccount.setBalance(50000.0);
		companyInfo.setCompanyAccount(companyAccount);

		SalaryRule bossSalaryRule = new SalaryRule();
		bossSalaryRule.setNo("33");
		bossSalaryRule.setEmpLevel(EmpLevel.boss);
		bossSalaryRule.setName("老板的薪资规则");
		bossSalaryRule.setSalaryPercent(70);
		bossSalaryRule.setBonusPercent(30);

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

		salaryRuleSet.add(bossSalaryRule);
		salaryRuleSet.add(managerSalaryRule);
		salaryRuleSet.add(empSalaryRule);

		Company company = new Company(companyInfo, salaryRuleSet);

		company.open();

		Emp zhangsan = new Emp();
		zhangsan.setEmpName("张三");
		zhangsan.setWorkforceCapability(85);
		Account zhangsanAccount = new Account();
		zhangsanAccount.setAccountNumber("zhangsanA003");
		zhangsanAccount.setBalance(0.0);
		zhangsan.setEmpAccount(zhangsanAccount);

		try {
			company.recruit(zhangsan, 10000.0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Emp lisi = new Emp();
		lisi.setEmpName("李四");
		lisi.setWorkforceCapability(70);

		Account lisiAccount = new Account();
		lisiAccount.setAccountNumber("lisiB008");
		lisiAccount.setBalance(0.0);
		lisi.setEmpAccount(lisiAccount);

		try {
			company.recruit(lisi, 8000.0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Emp wangwu = new Emp();
		wangwu.setEmpName("王五");
		wangwu.setWorkforceCapability(120);

		Account wangwuAccount = new Account();
		wangwuAccount.setAccountNumber("wangwuB04545");
		wangwuAccount.setBalance(0.0);
		wangwu.setEmpAccount(wangwuAccount);

		try {
			company.recruit(wangwu, 10000.0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(company.queryCompanyInfo());

		company.business();

		System.out.println("it is time to pay money for  emp ");
		company.getCompanyInfo().getBoss().setEmpMood(EmpMood.crazy);

		SimpleDateFormat sendDate = new SimpleDateFormat("yyyyMMdd");

		company.payoffemp(sendDate.format(new Date()));

	}
}
