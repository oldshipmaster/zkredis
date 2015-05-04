package org.zk.redis.detector.common;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.zk.redis.detector.client.RedisClient;
import org.zk.redis.detector.client.RedisClientPoolManager;
import org.zk.redis.detector.dao.RedisConfigDao;
import org.zk.redis.detector.model.RedisConfigDto;
import org.zk.redis.detector.util.AppContext;
import org.zk.redis.detector.zookeeper.DefaultZookeeperClient;
import org.zk.redis.detector.zookeeper.handler.CreateGroupNodeHandler;
import org.zk.redis.detector.zookeeper.pool.ZookeeperPoolManager;

/**
 * 
 * @author captain.guo
 *	初始化工作线程池
 */
public class InitThreadServlet  extends HttpServlet{
	private static final long serialVersionUID = 4577187779574873593L;
	protected static final Logger logger = Logger.getLogger(InitThreadServlet.class);
	public static final int THREADPOOL_MAX_SIZE   = 25;
	public static final int THREADPOOL_KEEP_ALIVE = 30;
	private static volatile int THREAD_INDEX = 1;
	//由JVM根据系统的情况(即CPU的核数)来决定IO Processor的线程的数量
	public static final int THREADPOOL_CORE_SIZE  = Runtime.getRuntime().availableProcessors();
	
	public static final ThreadPoolExecutor THREAD_EXECUTOR = 
			new ThreadPoolExecutor(THREADPOOL_CORE_SIZE,THREADPOOL_MAX_SIZE,THREADPOOL_KEEP_ALIVE,
					TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(),new ThreadFactory() {
		
		@Override
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r,"hearbeat-thread-" + THREAD_INDEX++);
			thread.setDaemon(true);
			return thread;
		}
	});
	
	@Override
	public void init() throws ServletException {
		doWorks(AppContext.getBean(RedisConfigDao.class),AppContext.getBean(RedisClientPoolManager.class));
	}
	
	public void doWorks(RedisConfigDao redisConfigDao,RedisClientPoolManager poolManager){
		//加载所有的redis连接信息
		List<RedisConfigDto> list = redisConfigDao.getAll();
		for(RedisConfigDto dto : list){
			RedisClient client = new RedisClient(dto.getHost(),dto.getPort(),dto.getUsername(),dto.getPassword(),dto.getRedismode(),dto.getStatus());
			//启动redis心跳
			InitThreadServlet.THREAD_EXECUTOR.execute(client);
			
		}

		//启动redisClient队列检测
		poolManager.init();
		//初始化zookeeper对象连接池
		 ZookeeperPoolManager.getInstance().init();
		 //创建父节点
		 try {
			DefaultZookeeperClient.getInstance().execute(new CreateGroupNodeHandler());
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		
	}
	
}
