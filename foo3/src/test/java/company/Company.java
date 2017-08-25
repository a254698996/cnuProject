package company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Company {

	private CompanyInfo companyInfo;
	private Set<SalaryRule> empSalaryRuleSet;

	private final static double CRAZY_PERCENT = 1.0;
	private final static double NORMAL_PERCENT = 0.8;

	public Company(CompanyInfo companyInfo, Set<SalaryRule> empSalaryRuleSet) {
		this.companyInfo = companyInfo;
		this.empSalaryRuleSet = empSalaryRuleSet;
	}

	public void payoffemp(String sendDate) {
		System.out.println(" should to pay all emp ");
		List<EmpSalaryBillWater> billWaterList = calcEmpSalaryBillWater(sendDate);
		for (EmpSalaryBillWater empSalaryBillWater : billWaterList) {
			System.out.println(empSalaryBillWater);
		}
		payoffEmp(this, billWaterList);
	}

	private void payoffEmp(Company company, List<EmpSalaryBillWater> billWaterList) {
		Account companyAccount = company.getCompanyInfo().getCompanyAccount();
		// 如果 公司账户上的钱 不足 发给 全部员 工 是否 给部分 员工 先发工资（100%） ， 还 是给 全部员工发放工资的 80% 或 更少
		// 一部分 ，等有钱了再发
		normalPay(companyAccount, billWaterList);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<Emp> empManageSet = company.getCompanyInfo().getEmpManageSet();
		empCheckAccount(empManageSet);
		
		Set<Emp> empSet = company.getCompanyInfo().getEmpSet();
		empCheckAccount(empSet);
	}

	// 正常 按 工资条 全额发放
	private void normalPay(Account companyAccount, List<EmpSalaryBillWater> billWaterList) {
		ExecutorService threadPool = Executors.newFixedThreadPool(billWaterList.size());
		for (EmpSalaryBillWater empSalaryBillWater : billWaterList) {
			PayMoney payMoney = new PayMoney(companyAccount, empSalaryBillWater.getEmp().getEmpAccount(),
//					。。 empSalaryBillWater.getEmp().getEmpAccount()这个参数 是null 
					empSalaryBillWater.getRealbonus() + empSalaryBillWater.getRealSalary());
			threadPool.submit(payMoney);
		}
		try {
			while (!threadPool.awaitTermination(500, TimeUnit.MILLISECONDS)) {
				System.out.println("工资还没有发完");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void empCheckAccount(Set<Emp> empSet) {
		for (Emp emp : empSet) {
			Account empAccount = emp.getEmpAccount();
			System.out.println(empAccount);
		}
	}

	private List<EmpSalaryBillWater> calcEmpSalaryBillWater(String sendDate) {
		Emp boss = this.companyInfo.getBoss();
		double percent = calcBossPercent(boss);
		return calcEmpSalaryBillWater(sendDate, percent);
	}

	private double calcBossPercent(Emp boss) {
		if (boss.getEmpMood().equals(EmpMood.crazy)) {
			return CRAZY_PERCENT;
		}
		return NORMAL_PERCENT;
	}

	private List<EmpSalaryBillWater> calcEmpSalaryBillWater(String sendDate, double percent) {
		List<EmpSalaryBillWater> empSalaryBillWaterList = new ArrayList<EmpSalaryBillWater>();

		Set<Emp> empManageSet = this.companyInfo.getEmpManageSet();
		calcEmpSalaryBillWater(sendDate, empSalaryBillWaterList, empManageSet, percent);

		Set<Emp> empSet = this.companyInfo.getEmpSet();
		calcEmpSalaryBillWater(sendDate, empSalaryBillWaterList, empSet, percent);

		return empSalaryBillWaterList;
	}

	private void calcEmpSalaryBillWater(String sendDate, List<EmpSalaryBillWater> empSalaryBillWaterList,
			Set<Emp> empManageSet, double percent) {
		if (empManageSet != null && !empManageSet.isEmpty()) {
			Iterator<Emp> iterator = empManageSet.iterator();
			while (iterator.hasNext()) {
				Emp emp = iterator.next();
				EmpOffer empOffer = emp.getEmpOffer();
				EmpSalaryBillWater billWater = calcEmpSalaryBillWater(empOffer, percent, sendDate);
				empSalaryBillWaterList.add(billWater);
			}
		}
	}

	private EmpSalaryBillWater calcEmpSalaryBillWater(EmpOffer empOffer, double percent, String sendDate) {
		EmpSalaryBillWater billWater = new EmpSalaryBillWater();
		SalaryRule empSalaryRule = empOffer.getEmpSalaryRule();
		double salaryPercent = empSalaryRule.getSalaryPercent() * percent;
		double bonusPercent = empSalaryRule.getBonusPercent() * percent;
		billWater.setRealSalary(empOffer.getMoney() * salaryPercent);
		billWater.setRealbonus(empOffer.getMoney() * bonusPercent);
		billWater.setSendDate(sendDate);
		return billWater;
	}

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

		if (emp.getEmpLevel().equals(EmpLevel.boss)) {
			this.companyInfo.addOneManager(emp);
		}

		if (emp.getEmpLevel().equals(EmpLevel.manger)) {
			this.companyInfo.addOneManager(emp);
		}

		if (emp.getEmpLevel().equals(EmpLevel.normal)) {
			this.companyInfo.addOneEmp(emp);
		}

		EmpOffer empOffer = getEmpOffer(emp, money);
		emp.setEmpOffer(empOffer);

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
		if (workforceCapability > 100) {
			return EmpLevel.boss;
		}
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
