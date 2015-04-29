package org.zk.redis.detector.zookeeper.handler;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.data.Stat;
import org.zk.redis.detector.zookeeper.AbstractZookeeperHandler;

/**
 * 在zookeeper获取节点数据
 * 
 * @author captain.guo
 * 
 */
public class GetDataHandler extends AbstractZookeeperHandler {

	public GetDataHandler(String id) {
		super(id);
	}

	@Override
	public <T> T handle() throws Exception {
		String path = id;

		if (!StringUtils.startsWithIgnoreCase(id, GROUP_NAME)) {
			path = GROUP_NAME + NODE_SEP + id;
		}
		if (null != zookeeper) {
			Stat stat = zookeeper.exists(path, false);
			if (null == stat) {
				Object redisConfig = null;
				byte[] data = zookeeper.getData(path, false, null);
				redisConfig = SerializationUtils.deserialize(data);
				return (T) redisConfig;
			} else {
				logger.debug("{}---redis-info数据不存在!" + path);
			}
		}
		return (T) null;
	}

}
