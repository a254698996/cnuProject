package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolTest {

	public static void main(String[] args) {
		final Account fromAccount = new Account(9988, 50000, "fromAccount");
		final Lock lock = new ReentrantLock();

		List<Account> list = new ArrayList<>(1000);
		for (int i = 0; i < 1000; i++) {
			Account toAccount = new Account(i, 0, "toAccount--" + i);
			list.add(toAccount);
		}
		int corePoolSize = 10;
		int maximumPoolSize = 20;
		int keepAliveTime = 5;
		TimeUnit unit = TimeUnit.MILLISECONDS;
		int queueSize = 80;
		final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(queueSize);
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
				unit, workQueue, new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println("r----  " + r + "  workQueue .size " + executor.getQueue().size());
						System.out.println();
					}
				});

		for (final Account account : list) {
			threadPoolExecutor.execute(new AccountWork(lock, fromAccount, account));
		}

		threadPoolExecutor.shutdown();

		for (Account account : list) {
			System.out.println(account);
		}

		System.out.println("fromAccount ---- " + fromAccount);
	}
}

class AccountWork implements Runnable {
	Lock lock;
	Account fromAccount, account;

	public AccountWork(Lock lock, Account fromAccount, Account account) {
		this.lock = lock;
		this.account = account;
		this.fromAccount = fromAccount;
	}

	public void run() {
		try {
			lock.lock();
			if (fromAccount.checkBalance(50)) {
				// Thread.sleep(200);
				fromAccount.add(-50);
				account.add(50);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

class Account {
	private int no;
	private double balance;
	private String name;

	public Account(int no, double balance, String name) {
		super();
		this.no = no;
		this.balance = balance;
		this.name = name;
	}

	public boolean checkBalance(double money) {
		return this.balance - money >= 0;
	}

	public void add(double money) {
		this.balance += money;
	}

	@Override
	public String toString() {
		return "Account [no=" + no + ", balance=" + balance + ", name=" + name + "]";
	}

}
