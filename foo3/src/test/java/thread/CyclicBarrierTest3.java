package thread;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest3 {

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
		C4 c = new C4(barrier);
		for (int i = 0; i < count; i++) {
			new Thread(c, "线程 --  " + (i + 1)).start();
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("CyclicBarrier  重用");
		for (int i = 0; i < count; i++) {
			new Thread(c, "线程 --  " + (i + 1)).start();
		}
		System.out.println("main Thread go on execute ");
		System.out.println("main Thread end ");
	}

}

class C4 implements Runnable {
	private CyclicBarrier cdl;

	public C4(CyclicBarrier cdl) {
		this.cdl = cdl;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + " 执行了第一部 ...");
			cdl.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " 执行了第 二  部 ...");
	}

}
