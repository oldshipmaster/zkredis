package org.zk.redis.detector.dao;

import java.util.List;

import org.zk.redis.detector.ehcache.CacheConstants;
import org.zk.redis.detector.ehcache.UseCache;
import org.zk.redis.detector.model.RedisConfigDto;

/**
 * 
 * @author captain.guo
 *
 */
public interface RedisConfigDao {
	@UseCache(group = "RedisConfigDao", timeout=CacheConstants.CACHE_24_HOUR)
		List<RedisConfigDto>getAll();
}
