package org.zk.redis.detector.zookeeper.handler;

/**
 * 在zookeeper上创建父节点
 * @author captain.guo
 *
 */
public class CreateGroupNodeHandler  extends CreateNodeHandler{

	
	public CreateGroupNodeHandler(){
		this(GROUP_NAME);
	}
	
	public CreateGroupNodeHandler(String id) {
		super(id,null);
	}

	
}
