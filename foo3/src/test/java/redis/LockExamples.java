package redis;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.misc.URIBuilder;
 

public class LockExamples {

	public static void main(String[] args) throws InterruptedException {
		// testRedis();
		// test();
		// hehe();
		// testUri();
		testLock();
	}

	private static void testUri() {
		String uri = "redis://10.153.29.54:26379";
		URI create = URIBuilder.create(uri);
		int port = create.getPort();
		System.out.println("port  " + port);
		String host = create.getHost();
		System.out.println("host  " + host);

		String s = uri.substring(0, uri.lastIndexOf(":")).replaceFirst("redis://", "").replaceFirst("rediss://", "");
		System.out.println("s  " + s);
		URI create2 = URI.create(uri.replace(s, "[" + s + "]"));
	}

	public static void test() {
		String key = "tarzan9991111";
		String value = "tarzan9991111";
		String set = foo3.example.redis.JedisSentinelUtil.getInstance().strings().set(key, value);
		System.out.println("set return " + set);
		foo3.example.redis.JedisSentinelUtil.getInstance().keys().del(key);

	}

	private static void hehe() {
		Config config = new Config();
		config.useSingleServer().setAddress("10.153.29.54:6379");
		// .setSlaveConnectionPoolSize(10000).addNodeAddress("10.153.29.54:16379",
		// "10.153.29.54:26379");
		RedissonClient redissonClient = Redisson.create(config);
		RMap<String, String> rMap = redissonClient.getMap("HelloWorld");
		String put = rMap.put("rancho945", "Hello Redis");
		System.out.println("put  " + put);
		// //获取并输出
		System.out.println("get   " + rMap.get("rancho945"));
		rMap.delete();
	}

	private static void testRedis() {
		Config config = new Config();
		// 使用单机配置，并设置redis地址和端口
		String[] addresses = new String[] { "redis://10.153.29.54:16379", "redis://10.153.29.54:26379",
				"redis://10.153.29.55:16379" };
		config.useSentinelServers().setMasterName("mymaster").addSentinelAddress(addresses);
		// 通过配置创建客户端
		RedissonClient redissonClient = Redisson.create(config);
		// 获取一个RMap
		RMap<String, String> rMap = redissonClient.getMap("HelloWorld");
		// //往map里写数据
		String put = rMap.put("rancho945", "Hello Redis");
		System.out.println("put  " + put);
		// //获取并输出
		System.out.println("get   " + rMap.get("rancho945"));
		boolean delete = rMap.delete();
		System.out.println("delete  " + delete);
		System.out.println("get   " + rMap.get("rancho945"));
	}

	private static void testLock() throws InterruptedException {
		Config config = new Config();
		// 使用单机配置，并设置redis地址和端口
		String[] addresses = new String[] { "redis://10.153.29.54:16379", "redis://10.153.29.54:26379",
				"redis://10.153.29.55:16379" };
		config.useSentinelServers().setMasterName("mymaster").addSentinelAddress(addresses);
		// 通过配置创建客户端
		final RedissonClient redisson = Redisson.create(config);
		//
		final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		RLock lock = redisson.getLock("lock");
//		lock.lock(5, TimeUnit.SECONDS);
		lock.lock();
		
		System.out.println("走你..... ");
		Thread.sleep(5000);
		System.out.println("execute  "+sdf.format(new Date()));
		lock.unlock();
		System.out.println("begin  "+sdf.format(new Date()));
		Thread t = new Thread() {
			public void run() {
				RLock lock1 = redisson.getLock("lock");
				lock1.lock();
				System.out.println("getLock  "+sdf.format(new Date()));
				System.out.println("哈哈哈 。。。。。 ");
				lock1.unlock();
			};
		};

		t.start();
		t.join();

	
		System.out.println("end  "+sdf.format(new Date()));
		//
		redisson.shutdown();
	}
}