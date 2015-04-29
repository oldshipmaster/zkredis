package org.zk.redis.detector.zookeeper;

import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.zk.redis.detector.zookeeper.pool.ZookeeperPoolManager;

public class DefaultZookeeperClient implements ZookeeperClient {
	/** 日志 */
	private static final Logger LOGGER = Logger
			.getLogger(ZookeeperClient.class);

	/** 单例对象 */
	private static ZookeeperClient instance;

	/** ZK对象池 */
	private ZookeeperPoolManager pool;

	/**
	 * 构造方法
	 */
	protected DefaultZookeeperClient() {
		if (pool == null) {
			pool = ZookeeperPoolManager.getInstance();
		}
	}

	/**
	 * 返回单例方法
	 * 
	 * @return
	 */
	public static ZookeeperClient getInstance() {
		if (instance == null) {
			instance = new DefaultZookeeperClient();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T execute(ZookeeperHandler handler) throws Exception { 
		  //从池中获取ZK对象 
        ZooKeeper zk = pool.borrowObject();
		if (zk != null) {
			try {
				handler.setZookeeper(zk);
				return (T) handler.handle();
			} catch (KeeperException ex) {
				LOGGER.error("执行ZK节点操作时发生异常: ", ex);
			} catch (InterruptedException ex) {
				LOGGER.error("执行ZK节点操作时发生异常: ", ex);
			} finally {
				// 将ZK对象返回对象池中
				pool.returnObject(zk);
			}
		}
    return (T) null;}

}
