package foo3.example.redis;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import redis.clients.jedis.Jedis;

public class TestRedisReConn {

	private static boolean isRun = false;
	private static Timer timer;
	private static TimerTask timerTask;

	public static void main(String[] args) {
		try {
			Jedis jredis = JedisSentinelUtil_ReConn.getJredis();
			System.out.println("调用 redis ... ");
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			testOptionRedis();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testOptionRedis() throws Exception {
		while (!isRun) {
			try {
				optionRedis();
				break;
			} catch (Exception e) {
				System.out.println("optionRedis fail.. ");
			}
		}
		try {
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void optionRedis() {
		if (isRun) {
			return;
		}
		timer = new Timer();
		timerTask = new TimerTask() {
			public void run() {
				try {
					System.out.println(".....  操作 redis .....  "+JedisSentinelUtil_ReConn.isStart);
					if (JedisSentinelUtil_ReConn.isStart) {
						Jedis jredis = JedisSentinelUtil_ReConn.getJredis();
						String set = jredis.set("heheh", "走你 ");
						System.out.println("redis set  " + set);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						String string = jredis.get("heheh");
						System.out.println("redis get  " + string);
						Long del = jredis.del("heheh");
						System.out.println("redis del  " + del);
						jredis.close();
					}
				} catch (Exception e) {
					System.out.println("option redis error ");
					isRun = false;
				}
			}
		};
		timer.schedule(timerTask, 1000 * 10, 1000 * 10);
		isRun = true;
	}

}