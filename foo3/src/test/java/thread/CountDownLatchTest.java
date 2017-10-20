package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

	static CountDownLatch cdl = new CountDownLatch(2);

	public static void main(String[] args) {
		System.out.println("main thread begin ---");
		C c = new C(cdl);
		new Thread(c, "线程 --aAA").start();
		new Thread(c, "线程 --BBBB").start();
		try {
			cdl.await(6900, TimeUnit.MILLISECONDS);
			System.out.println("main Thread go on execute ");
			System.out.println("main Thread end ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class C implements Runnable {
	private CountDownLatch cdl;

	public C(CountDownLatch cdl) {
		this.cdl = cdl;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + " ");
			cdl.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
