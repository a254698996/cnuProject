package web.controller;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.entity.SeqnoEntity;
import web.entity.ZookeeperEntity;
import web.service.ISeqnoService;
import web.service.IZookeeperService;
import web.zookeeper.SeqnoClientThatLocks;

@Controller
@RequestMapping("/zookeeper")
public class ZookeeperController {
	Logger logger = LoggerFactory.getLogger(ZookeeperController.class);

	@Autowired
	@Lazy
	CuratorFramework client;
	@Autowired
	@Lazy
	ISeqnoService<SeqnoEntity, Serializable> seqnoService;
	@Autowired
	@Lazy
	IZookeeperService<ZookeeperEntity, Serializable> zookeeperService;

	private String lockPath = "/locks";

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public void indexNotice(@PathVariable Integer id) {
		ZookeeperEntity zookeeperEntity = new ZookeeperEntity();
		zookeeperEntity.setName("AAA");
		zookeeperEntity.setNum(99);
		SeqnoClientThatLocks seqnoClientThatLocks = new SeqnoClientThatLocks(client, lockPath, seqnoService, "client",
				zookeeperEntity, zookeeperService);
		try {
			seqnoClientThatLocks.doWork(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
