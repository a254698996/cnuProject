/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package web.zookeeper;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import web.entity.SeqnoEntity;
import web.entity.ZookeeperEntity;
import web.service.ISeqnoService;
import web.service.IZookeeperService;

public class SeqnoClientThatLocks {
	private final InterProcessMutex lock;
	private final ISeqnoService<SeqnoEntity, Serializable> seqnoService;
	private final String clientName;
	private final ZookeeperEntity zookeeperEntity;
	private final IZookeeperService<ZookeeperEntity, Serializable> zooKeeperServer;

	public SeqnoClientThatLocks(CuratorFramework client, String lockPath, ISeqnoService seqnoService, String clientName,
			ZookeeperEntity zookeeperEntity, IZookeeperService zooKeeperServer) {
		this.seqnoService = seqnoService;
		this.clientName = clientName;
		this.zookeeperEntity = zookeeperEntity;
		this.zooKeeperServer = zooKeeperServer;
		lock = new InterProcessMutex(client, lockPath);
	}

	public void doWork(long time, TimeUnit unit) throws Exception {
		if (!lock.acquire(time, unit)) {
			throw new IllegalStateException(clientName + " could not acquire the lock");
		}
		try {
			System.out.println(clientName + " has the lock");
			int seqno = seqnoService.getSeqno();
			zookeeperEntity.setId(seqno);
			zooKeeperServer.save(zookeeperEntity);
		} finally {
			System.out.println(clientName + " releasing the lock");
			lock.release(); // always release the lock in a finally block
		}
	}
}
