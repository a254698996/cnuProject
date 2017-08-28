package web.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(value = false)
public class CuratorHelp {

	private static CuratorFramework client;

	public CuratorHelp() {
		// 连接 zookeeper-3.5.3-beta ok
		// 连接 zookeeper-3.3.6
		
		//  curator-recipes 4.0.0  和  curator-recipes 3.3.0 连接  zookeeper-3.5.3-beta ok
		//  curator-recipes 4.0.0  和  curator-recipes 3.3.0  不能正常连接  zookeeper-3.3.6
		
		// <dependency>
		// <groupId>org.apache.curator</groupId>
		// <artifactId>curator-recipes</artifactId>
		// <version>4.0.0</version>
		// </dependency>
		client = CuratorFrameworkFactory.newClient("127.0.0.1:9181", new ExponentialBackoffRetry(1000, 3));
		// 以下为必须  如 client 没有 start 无法获得 分步式锁  InterProcessMutex
		client.start();
	}

	public static InterProcessMutex getInterProcessMutex(String lockPath) {
		return new InterProcessMutex(client, lockPath);
	}

}