package org.zk.redis.detector.zookeeper;

import org.apache.zookeeper.ZooKeeper;
/**
 * zookeeper客户端操作接口
 * @author captain.guo
 *
 */
public interface ZookeeperHandler {
	
	 /** ZK组节点名称 */
    public static final String GROUP_NAME = "/REDISCONFIG";
    
	public <T> T handle() throws Exception;

	public void setZookeeper(ZooKeeper zookeeper);
}
