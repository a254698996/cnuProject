package company;

public class PayMoney implements Runnable {

	private Account outAccount;

	private Account inAccount;

	double payMoney;

	public PayMoney(Account outAccount, Account inAccount, double payMoney) {
		super();
		this.outAccount = outAccount;
		this.inAccount = inAccount;
		this.payMoney = payMoney;
	}

	private synchronized void transAccount() {
		this.outAccount.setBalance(this.outAccount.getBalance() - this.payMoney);
		this.inAccount.setBalance(this.inAccount.getBalance() + this.payMoney);
	}

	@Override
	public void run() {
		transAccount();
	}
}
