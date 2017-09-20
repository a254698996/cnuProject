package foo3.example.redis;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class JedisSentinelUtil_ReConn {

	private static final Logger logger = LoggerFactory.getLogger(JedisSentinelUtil_ReConn.class);

	// Redis服务器IP
	public static String ADDR;
	// 访问密码
	public static String AUTH;

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	public static int MAX_TOTAL;
	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	public static int MAX_IDLE;
	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	public static int MAX_WAIT;
	// 读取超时
	public static int TIMEOUT;
	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	public static boolean TEST_ON_BORROW = true;
	// 读取超时
	public static int MAXATTEMPTS;

	public static int DEFAULT_EXPIRE;

	private static JedisPoolConfig config;

	private static Set<String> sentinels;

	private static JedisSentinelPool redisSentinelJedisPool;

	public static boolean isStart = false;

	private static boolean tryReconntionIng = false;

	private static Timer timer;

	static {
		if (!isStart) {
			System.out.println("11111111111111111");
			start();
		}
	}

	public static void start() {
		try {
			Properties prop = new Properties();
			// 注意这里 redisSentinel.properties 路径 下面的写法为和 当前
			// JedisSentinelUtil_ReConn.class 相同路径下
			InputStream input = JedisSentinelUtil_ReConn.class.getResourceAsStream("redisSentinel.properties");
			// 这个路径 为资源 目录的根路径   "/redisSentinel.properties"
			prop.load(new BufferedInputStream(input));
			AUTH = prop.getProperty("AUTH");
			MAX_TOTAL = Integer.valueOf(prop.getProperty("MAX_TOTAL"));
			MAX_IDLE = Integer.valueOf(prop.getProperty("MAX_IDLE"));
			MAX_WAIT = Integer.valueOf(prop.getProperty("MAX_WAIT"));
			TIMEOUT = Integer.valueOf(prop.getProperty("TIMEOUT"));
			DEFAULT_EXPIRE = Integer.valueOf(prop.getProperty("DEFAULT_EXPIRE"));
			MAXATTEMPTS = Integer.valueOf(prop.getProperty("MAXATTEMPTS"));

			config = new JedisPoolConfig();
			config.setMaxTotal(MAX_TOTAL);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);

			sentinels = new HashSet<String>();
			ADDR = prop.getProperty("ADDR");
			String[] split = ADDR.split(";");
			for (String string : split) {
				System.out.println("address  " + string);
				sentinels.add(string);
			}
			String clusterName = "mymaster";
			if (StringUtils.isNotBlank(AUTH)) {
				redisSentinelJedisPool = new JedisSentinelPool(clusterName, sentinels, config, AUTH);
			} else {
				redisSentinelJedisPool = new JedisSentinelPool(clusterName, sentinels, config);
			}
			isStart = true;
			System.out.println("23456789456789");
		} catch (Exception e) {
			e.printStackTrace();
			reConntion();
			logger.error("初始缓存连接：" + e.getMessage());
		}
	}

	public static Jedis getJredis() {
		Jedis jedis = null;
		try {
			jedis = redisSentinelJedisPool.getResource();
		} catch (Exception e) {
			isStart = false;
			reConntion();
			logger.error(e.getMessage());
		}
		return jedis;
	}

	private synchronized static void reConntion() {
		if (tryReconntionIng) {
			logger.info("reconnetion to redis hold on ...  ");
			return;
		}
		tryReconntionIng = true;

		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				logger.info("reconnetion to redis ..... ");
				start();
				if (isStart) {
					logger.info("reconnetion to redis success. ");
					timer.cancel();
					tryReconntionIng = false;
				}
			}
		}, 1000 * 5, 1000 * 30);
	}

}