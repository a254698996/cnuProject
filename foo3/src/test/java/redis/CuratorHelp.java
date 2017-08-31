package redis;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

//@Component
//@Lazy(value = false)
public class CuratorHelp {

	private static CuratorFramework client;

	public CuratorHelp() {
		client = CuratorFrameworkFactory.newClient("127.0.0.1:9181", new ExponentialBackoffRetry(1000, 3));
		client.start();
	}

	public static InterProcessMutex getInterProcessMutex(String lockPath) {
		return new InterProcessMutex(client, lockPath);
	}

}
