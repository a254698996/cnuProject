package jvm;

public class JoinTest {
	public static void main(String[] args) {
		System.out.println("main Thread  start  ");
		for (int i = 0; i < 5; i++) {
			Join join = new Join("Thread - " + i);
			join.start();
			try {
				join.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("main Thread  end ");
	}
}

class Join extends Thread {

	public Join(String arg0) {
		super(arg0);
	}

	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + " execute start ..");
			Thread.sleep(500);
			System.out.println(Thread.currentThread().getName() + " execute end .... ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}