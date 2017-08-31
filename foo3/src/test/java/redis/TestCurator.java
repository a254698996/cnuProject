package redis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCurator {

	public static void main(String[] args) {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(50);
		for (int i = 0; i < 50; i++) {
			newFixedThreadPool.execute(new PostTest());
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		newFixedThreadPool.shutdown();
	}

	public static class PostTest implements Runnable {

		@Override
		public void run() {
			String s = HttpRequest.sendGet("http://localhost/MyApp/haha.jsp", "key=123&v=456");
			System.out.println(s);
		}
	}
}
