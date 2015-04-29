package org.zk.redis.detector.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *  系统上下文类，主要有以下用处：
 * ·系统启动后做一些初始化工作；
 * ·注入了spring的ApplicationContext，可以方便地获取spring容器中的对象
 * ·提供了获取request和资源绝对路径的方法
 * @since 2011-12-20
 */
public class AppContext implements ApplicationContextAware {
	
	private static ApplicationContext context;
	
	/**
	 * 获取spring容器中的对象, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) context.getBean(name);
	}

	/**
	 * 获取spring容器中的对象, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		return context.getBean(requiredType);
	}
	
	public static <T> T getBean(String name, Class<T> requiredType) {
		try {
			T t = context.getBean(name, requiredType);
			return t;
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		AppContext.context = context;
	}
}
