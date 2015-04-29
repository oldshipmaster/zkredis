package org.zk.redis.detector.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 
 * @author captain.guo 此类用来处理，心跳
 * 
 */
public class RedisClient implements Runnable {
	private final static Logger logger = LoggerFactory.getLogger(RedisClient.class);
	private JedisPoolConfig config;
	private JedisPool pool;
	private String host;
	private int port;
	private boolean stop = true;
	private int failTimes;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getFailTimes() {
		return failTimes;
	}

	public void setFailTimes(int failTimes) {
		this.failTimes = failTimes;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public RedisClient(String host, int port) {
		config = new JedisPoolConfig();
		config.setMaxIdle(10);
		config.setMaxTotal(30);
		config.setMaxWaitMillis(3000);
		pool = new JedisPool(config, host, port);
		this.host = host;
		this.port = port;
		this.failTimes = 0;
	}

	@Override
	public void run() {
		while (stop) {
			try {
				logger.debug("stop--------->" + stop);

				long start = System.currentTimeMillis();
				logger.debug("host--{}--port--{}--start--{}", host, port);
				Jedis client = pool.getResource();
				String result = client.ping();

				Thread.sleep(5000);
				long end = System.currentTimeMillis();
				logger.debug("host--{}--port--{}--end--{}----->{}------{}",host, port, (end - start), result,
						RedisClientPoolManager.redisClientQueue.size());

			} catch (InterruptedException e) {
				this.failTimes++;
				logger.debug(e.getMessage(), e);
			} catch (JedisConnectionException e) {
				this.failTimes++;
				logger.debug(e.getMessage(), e);
			} finally {
				RedisClientPoolManager.redisClientQueue.offer(this);
				logger.debug("finally---------->{}"
						+ RedisClientPoolManager.redisClientQueue.size());
			}
		}
		logger.debug("stop-----finally---->" + stop);
	}

}
