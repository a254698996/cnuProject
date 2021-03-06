package foo3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author peace
 *
 */
public class DistributedLockTest {
	public static void main(String[] args) throws InterruptedException {
		DistributedLock lock = new DistributedLock("127.0.0.1:2181", "lock");
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(100);
		Person person = new Person();
		for (int i = 0; i < 5; i++) {
			newFixedThreadPool.execute(new AddMoney(person, 1, lock));
		}

		Thread.sleep(5000);

		newFixedThreadPool.shutdown();
		System.out.println("person.getMoney():" + person.getMoney());
	}
}

class AddMoney implements Runnable {
	private Person p;
	int money;
	DistributedLock lock;

	public AddMoney(Person p, int money, DistributedLock lock) {
		this.p = p;
		this.money = money;
		this.lock = lock;
	}

	@Override
	public void run() {
		// synchronized (p) {
		// p.setMoney(p.getMoney() + money);
		// }

		lock.lock();
		// 共享资源
		p.setMoney(p.getMoney() + money);
		if (lock != null) {
			lock.unlock();
		}
	}

}

class Person {
	private int money;

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}