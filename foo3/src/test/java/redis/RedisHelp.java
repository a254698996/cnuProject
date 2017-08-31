package redis;

import java.util.Random;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

//@Component
//@Lazy(value = false)
public class RedisHelp {

	private static RedissonClient redisson;

	static {
		Config config = new Config();
		// 使用单机配置，并设置redis地址和端口
		String[] addresses = new String[] { "redis://10.153.29.54:16379", "redis://10.153.29.54:26379",
				"redis://10.153.29.55:16379" };
		config.useSentinelServers().setMasterName("mymaster").addSentinelAddress(addresses);
		redisson = Redisson.create(config);
	}

	public static synchronized RedissonClient getRedissonClient() {
		return redisson;
	}

	private static void testLock() {
		RedissonClient redissonClient = RedisHelp.getRedissonClient();
		RLock lock = redissonClient.getLock("qwe_21212");
		lock.lock();
		System.out.println("hello world ");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
		redissonClient.shutdown();
	}

	public static void main(String[] args) {
		delayedStartUp();
	}

	private static void delayedStartUp() {
		for (int i = 0; i < 100; i++) {
			System.out.println(getRandom());
		}

	}

	private static int getRandom() {
		int max = 10000;
		int min = 1000;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return s;
	}

	// public synchronized void lock(String lockPath) {
	// RLock lock = redisson.getLock(lockPath);
	// lock.lock();
	// }
	//
	// public synchronized void unLock(String lockPath) {
	// RLock lock = redisson.getLock(lockPath);
	// lock.unlock();
	// }

}
