package org.zk.redis.detector.dao;

import java.util.List;

import org.zk.redis.detector.model.RedisConfigDto;

/**
 * 
 * @author captain.guo
 *
 */
public interface RedisConfigDao {
	
		List<RedisConfigDto>getAll();
}
