package org.zk.redis.detector.zookeeper;


/**
 * zookeeper客户端
 * @author captain.guo
 *
 */
public interface ZookeeperClient {
	 public <T> T execute(ZookeeperHandler handler) throws Exception;
}
