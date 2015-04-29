package org.zk.redis.detector.client;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 
 * @author captain.guo 用来构造和每个redis连接的client对象池
 */
@Component
public class RedisClientPoolManager {

	private static final Logger logger = Logger.getLogger(RedisClientPoolManager.class);
	public final static ConcurrentLinkedQueue<RedisClient> redisClientQueue = new ConcurrentLinkedQueue<RedisClient>();
	private ScheduledExecutorService scheduleQueueThread = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
		
		@Override
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r, "scheduleThread");
			return thread;
		}
	});
	
	public  void init(){
		scheduleQueueThread.scheduleAtFixedRate(new QueueThread(), 10000, 5000, TimeUnit.MILLISECONDS);
	}
	/**
	 * 遍历队列，剔除掉，死掉的redis连接对象
	 *
	 */
	public class QueueThread implements Runnable{

		@Override
		public void run() {
			while (RedisClientPoolManager.redisClientQueue.size() > 0) {
				RedisClient client = RedisClientPoolManager.redisClientQueue.poll();
				if(client.getFailTimes() > 4){
					client.setStop(false);
				}
			}
		}
		
	}

}
