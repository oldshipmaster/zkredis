/**
 * MethodCacheInterceptor.java
 *
 * Copyright 2007 easou, Inc. All Rights Reserved.
 */
package org.zk.redis.detector.ehcache;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 通过spring基于方法的增强获取业务数据; 实现原理：面向切点
 * 
 */
public class MethodCacheInterceptor implements MethodInterceptor,
		InitializingBean, StaticCache {
	private CacheProvider cacheProvider;

	/**
	 * 主方法 如果某方法可被缓存就缓存其结果 方法结果必须是可序列化的(serializable)
	 * 
	 * @param invocation
	 *            MethodInvocation
	 * @return object
	 * @throws Throwable
	 *             异常
	 */
	public final Object invoke(final MethodInvocation invocation)
			throws Throwable {
//		String targetName = invocation.getThis().getClass().getName();
		String targetName = invocation.getMethod().getDeclaringClass().getName();
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();
		UseCache uc = invocation.getMethod().getAnnotation(UseCache.class);
		long timeout = uc.timeout() * BASIC_TIME_UNIT; // 获取超时时间（毫秒单位）
		String group = uc.group(); // 缓存namspace
		Object result; // 返回结果
		String key = getCacheKey(targetName, methodName, arguments);
		result = cacheProvider.getValueFromCache(key, group, timeout); // 缓存获取结果
		if (result == null || "timeout".equals(result)) { // 如果缓存超时或获取不到缓存元素
			try {
				result = invocation.proceed();
			} catch (Exception e) {
				result = null;
			}
			if (null != result) { // 获取结果，并缓存
				cacheProvider.putValueToCache(key, (Serializable) result, group);
			} else { // 获取缓存之前内容
				result = cacheProvider.getBeforeValueFromCache(key, group);
				if (null == result) { // 之前缓存无key指定的value
					
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param targetName
	 *            类名
	 * @param methodName
	 *            方法名
	 * @param arguments
	 *            参数
	 * @return key values
	 */
	private String getCacheKey(final String targetName,
			final String methodName, final Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append("_").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append("_").append(arguments[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * InitializingBean 类property设置检测
	 * 
	 * @throws Exception
	 *             注册异常
	 */
	public final void afterPropertiesSet() throws Exception {
		Assert
				.notNull(
						cacheProvider,
						"A cacheProvider is required. Use setCacheProvider(cacheProvider) to provide one.");
	}

	/**
	 * 
	 * @param cacheProvider
	 *            CacheProvider
	 */
	public final void setCacheProvider(final CacheProvider cacheProvider) {
		this.cacheProvider = cacheProvider;
	}
}
