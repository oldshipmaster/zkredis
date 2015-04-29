package org.zk.redis.detector.zookeeper;

import org.apache.log4j.Logger;
import org.apache.zookeeper.ZooKeeper;

public abstract class AbstractZookeeperHandler implements ZookeeperHandler {
	protected static final Logger LOGGER = Logger
			.getLogger(ZookeeperHandler.class);

	/** ZK客户端 */
	protected ZooKeeper zookeeper;

	/**
	 * 节点ID
	 */
	protected String id;

	/**
	 * 构造方法
	 */
	public AbstractZookeeperHandler(String id) {
		this.id = id;
	}

	@Override
	public void setZookeeper(ZooKeeper zookeeper) {
		this.zookeeper = zookeeper;
	}
}
