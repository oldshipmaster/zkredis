package org.zk.redis.detector.zookeeper.handler;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.zk.redis.detector.model.RedisConfigZkData;
import org.zk.redis.detector.zookeeper.AbstractZookeeperHandler;
/**
 * 在zookeeper上创建节点
 * @author captain.guo
 *
 */
public class CreateNodeHandler  extends AbstractZookeeperHandler{

	private RedisConfigZkData data;
	public CreateNodeHandler(String id,RedisConfigZkData data) {
		super(id);
		this.data = data;
	}

	@Override
	public <T> T handle() throws Exception {
		String path = id;
		if (!StringUtils.startsWithIgnoreCase(id, GROUP_NAME)) {
            path = GROUP_NAME+NODE_SEP+id ;
        }
		if(null != zookeeper){
		Stat stat = 	zookeeper.exists(path, false);
			if (null == stat) {
				byte[] dataArr = null;
				if (null != this.data) {
					dataArr = SerializationUtils.serialize(this.data);
				}
				logger.debug("--------->"+zookeeper.getSessionId());
			String createPath = zookeeper.create(path, dataArr, Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			logger.debug("创建redis-info节点完成{}"+createPath);
			}else{
				logger.debug("redis-info节点已经创建完成，无需创建{}"+path);
			}
		}
		return  (T) null;
	}

}
