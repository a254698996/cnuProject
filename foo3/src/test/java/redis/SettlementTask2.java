package redis;

import java.io.Serializable;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.redisson.Redisson;
import org.redisson.api.RExecutorService;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
  

//@Component("settlementTask2")
//@Lazy(value = false)
public class SettlementTask2 {
	@Autowired
	private SettlementService settlementService;
	
	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	
	private final String lockPath="/examples/settlementLocks";
	
	private final InterProcessMutex lock = CuratorHelp.getInterProcessMutex(lockPath);

	private final String redisLockKey="/examples/settlementRedisLocks";
	
	public void testRedisTask2() {
		// 1. Create config object
//		SingleServerConfig config = new SingleServerConfig();
//
//		// 2. Create Redisson instance
//		RedissonClient redisson = Redisson.create(config);
//
//		// 3. Get object you need
//		RMap<MyKey, MyValue> map = redisson.getMap("myMap");
//
//		RLock lock = redisson.getLock("myLock");
//
//		RExecutorService executor = redisson.getExecutorService("myExecutorService");

		// over 30 different objects and services ...
	}
	
	public void testTask() {
		try {
			lock.acquire();
			settlementService.testTask();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				lock.release();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void testRedisTask() {
		RedisLock redisLock = new RedisLock(redisTemplate, redisLockKey, 10000, 20000);
		try {
			if (redisLock.lock()) {
				settlementService.testTask();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
			// 操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。 ————这里没有做
			redisLock.unlock();
		}
	}

	public void pay() {
		settlementService.pay();
	}

	public void secondSettlement() {
		settlementService.secondSettlement();
	}

	public void setSettlementFail() {
		settlementService.setSettlementFail();
	}

	public void refund() {
		settlementService.refund();
	}

}
