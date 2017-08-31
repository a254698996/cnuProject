package redis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class RedisLockTest {

	public static void main(String[] args) {
		testLocks();
	}

	private static void testLocks() {
		final String lockPath = "mylock";

		final RedissonClient redissonClient = RedisHelp.getRedissonClient();
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);

		newFixedThreadPool.execute(new Runnable() {

			@Override
			public synchronized void run() {
				RLock lock = redissonClient.getLock(lockPath);
				lock.lock();
				System.out.println(Thread.currentThread().getName() + "  .. is run ");
				lock.unlock();
			}
		});

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		newFixedThreadPool.shutdown();

	}
}