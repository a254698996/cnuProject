package thread;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	public static void main(String[] args) {
		testSemaphore();
	}

	private static void testSemaphore() {
		int works = 8;
		int resource = 5;
		Semaphore semaphore = new Semaphore(resource);
		for (int i = 0; i < works; i++) {
			new Work((i + 1), semaphore).start();
		}
	}
}

class Work extends Thread {
	private int num;
	private Semaphore semaphore;

	public Work(int num, Semaphore semaphore) {
		super();
		this.num = num;
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			semaphore.acquire();
			System.out.println("工人 " + this.num + " 获得了 机器正在生产");
			Thread.sleep(500);
			System.out.println("工人 " + this.num + " 释放了机器 ");
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
