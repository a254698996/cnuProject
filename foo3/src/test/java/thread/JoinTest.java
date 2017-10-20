package thread;

public class JoinTest {
	public static void main(String[] args) {
		// Thread t = new Thread(new RunnableImpl());
		// t.start();
		// try {
		// t.join(400);
		// System.out.println("join finish .");
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// --------------------------------
		// 下面的代码 有疑问 关于总执行时间
		long currentTimeMillis = System.currentTimeMillis();
		Thread t = new Thread(new RunnableImpl());
		new ThreadTest(t).start();
		t.start();
		try {
			t.join();
			System.out.println("joinFinish");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("use time " + (System.currentTimeMillis() - currentTimeMillis));
	}
}

class RunnableImpl implements Runnable {
	@Override
	public void run() {
		try {
			System.out.println(" begin sleep");
			Thread.sleep(4000);
			System.out.println(" end sleep");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ThreadTest extends Thread {

	Thread thread;

	public ThreadTest(Thread thread) {
		this.thread = thread;
	}

	@Override
	public void run() {
		synchronized (thread) {
			System.out.println("getObjectLock");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println("ReleaseObjectLock");
		}
	}
}