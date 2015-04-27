package org.zkredis.detector.common;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * 
 * @author captain.guo
 *	初始化工作线程池
 */
@Component
public class InitThreadFactory  {
	
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
	
	@PostConstruct 
	public void doWorks(){
		for(int i = 0;i <2;i++){
			THREAD_EXECUTOR.execute(new ThreadWork());
		}
	}
	
}
