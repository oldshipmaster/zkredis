package org.zk.redis.detector.zookeeper.handler;

import org.zk.redis.detector.model.RedisConfigZkData;
/**
 * 在zookeeper上创建父节点
 * @author captain.guo
 *
 */
public class CreateGroupNodeHandler  extends CreateNodeHandler{

	private RedisConfigZkData data;
	
	public CreateGroupNodeHandler(){
		this(GROUP_NAME);
	}
	
	public CreateGroupNodeHandler(String id) {
		super(id,null);
	}

	
}
