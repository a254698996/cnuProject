package thread;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	static CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {

		@Override
		public void run() {
			System.out.println(
					"哈哈哈。。 都 执行完了  第一部了。 下面 他们都 各自执行第二部 ...   这次是 线程  " + Thread.currentThread().getName() + " 触发的");
		}
	});

	public static void main(String[] args) {
		System.out.println("main thread begin ---");
		C2 c = new C2(barrier);
		new Thread(c, "线程 --aAAA").start();
		new Thread(c, "线程 --BBBB").start();
		System.out.println("main Thread go on execute ");
		System.out.println("main Thread end ");
	}

}

class C2 implements Runnable {
	private CyclicBarrier cdl;

	public C2(CyclicBarrier cdl) {
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
