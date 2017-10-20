package thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest2 {

	static CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {

		@Override
		public void run() {
			System.out.println(
					"哈哈哈。。 都 执行完了  第一部了。 下面 他们都 各自执行第二部 ...   这次是 线程  " + Thread.currentThread().getName() + " 触发的");
		}
	});

	public static void main(String[] args) {
		int count = 4;
		System.out.println("main thread begin ---");
		C3 c = new C3(barrier);
		for (int i = 0; i < count; i++) {
			if (i == count - 1) {
				try {
					Thread.sleep(1000);
					new Thread(c, "线程 --  " + (i + 1)).start();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				new Thread(c, "线程 --  " + (i + 1)).start();
			}
		}
		// new Thread(c, "线程 --aAAA").start();
		// new Thread(c, "线程 --BBBB").start();
		System.out.println("main Thread go on execute ");
		System.out.println("main Thread end ");
	}

}

class C3 implements Runnable {
	private CyclicBarrier cdl;

	public C3(CyclicBarrier cdl) {
		this.cdl = cdl;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + " 执行了第一部 ...");
			cdl.await(200, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " 执行了第 二  部 ...");
	}

}
