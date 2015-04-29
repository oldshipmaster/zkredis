/**
 * CacheProvider.java
 *
 * Copyright 2007 easou, Inc. All Rights Reserved.
 */
package org.zk.redis.detector.ehcache;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存提供者(声明为singletone模式)
 * Revision History
 */
public class CacheProvider implements StaticCache {
	
    private CacheManager cacheManager; // IOC 方式获取缓存管理器
    
    private int status = CACHE_USE_STATUS_DEFAULT; // 缓存所处模式

    /**
     * 默认构造函数
     */
    public CacheProvider() {

    }

    /**
     * 获取默认cache指定key的缓存内容
     * 
     * @param key 元素key值
     * @return Object
     */
    public final Object getValueFromCache(final String key) {
        return getValueFromCache(key, CACHE_NAME_DEFAULT);
    }

    /**
     * 获取指定cache指定key的缓存内容
     * 
     * @param key 元素key值
     * @param group cache name
     * @return Object
     */
    public final Object getValueFromCache(final String key, final String group) {
        return getValueFromCache(key, null == group ? CACHE_NAME_DEFAULT
                : group, -1);
    }

    /**
     * 获取namspace指定的cache缓存内容
     * 
     * @param key 键值
     * @param group namspace
     * @param timeout 超时时间
     * @return Object 序列化对象
     */
    public final Object getValueFromCache(final String key, final String group,
            final long timeout) {
        // 获取指定名称的cache
        Cache cache = getCache(group);
        if (null == cache) { // 找不到namspace的cache
            return null;
        }

        Element element = cache.get(key); // 获取指定key的元素
        if (null == element) { // 找不到元素，返回null
           // LOG.debug("cache not found with cacheKey:" + key);
            return null;
        }
        if (status == CACHE_NO_USE) { // 不使用缓存或缓存需更新
            return null;
        }
        if (timeout < 0 || status == CACHE_ONLY_USE) { // 缓存内容不过时、只使用缓存内容
            return element.getObjectValue();
        }
        if (status == CACHE_UPDATING) { // 缓存需更新
            removeGroup(group);
        }
        if (!isTimeOut(element, timeout)) { // 没有超时
            return element.getObjectValue();
        } else {
           // LOG.debug("cache is timeout");
            return "timeout";
        }

    }

    /**
     * 获取之前缓存内容,同时更新之前缓存创建时间
     * 
     * @param key 键值
     * @param group namspace
     * @return Object 序列化对象
     */
    public final Object getBeforeValueFromCache(final String key,
            final String group) {
        Cache cache = getCache(group);
        if (null == cache) {
            return null;
        }
        Element element = cache.get(key);
        if (null == element) {
            return null;
        }
        // 更新缓存管理器及相关元素
        updateCacheManager(element, cache, cacheManager);

        return element.getObjectValue();
    }

    /**
     * 将数据放入指定namspce缓存
     * 
     * @param key 键值
     * @param values 序列化对象
     */
    public final void putValueToCache(final String key, final Object values) {
        putValueToCache(key, values, CACHE_NAME_DEFAULT);
    }

    /**
     * 将数据放入指定namspce缓存
     * 
     * @param key 键值
     * @param values 序列化对象
     * @param group namspace
     */
    public final void putValueToCache(final String key, final Object values,
            final String group) {
        Cache cache = getCache(group);
        if (null == cache) {
            cacheManager.addCache(group);
            cache = cacheManager.getCache(group);
        }
        Element element = new Element(key, values);
        // 更新缓存管理器及相关元素
        updateCacheManager(element, cache, cacheManager);
    }

    /**
     * 更新缓存管理器及相关元素
     * 
     * @param element 缓存元素
     * @param cache 缓存
     * @param cacheManager 缓存管理器
     */
    private void updateCacheManager(final Element element, final Cache cache,
            final CacheManager cacheManager) {
    	element.getElementEvictionData().setCreationTime(System.currentTimeMillis());;
        cache.put(element);
        cache.setCacheManager(cacheManager);
    }

    /**
     * 获取指定namspace的cache
     * 
     * @param group namspace value
     * @return cache
     */
    private Cache getCache(final String group) {
        return cacheManager
                .getCache(null == group ? CACHE_NAME_DEFAULT : group);

    }

    /**
     * 检测缓存是否超时 uhh
     * 
     * @param element 缓存元素
     * @param time 超时时间
     * @return boolean
     */
    private boolean isTimeOut(final Element element, final long time) {
        if (System.currentTimeMillis() - (element.getCreationTime() + time) < 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 清除指定GROUP缓存
     * 
     * @param group 缓存名称
     */
    private void removeGroup(final String group) {
        String cacheName = (null == group ? CACHE_NAME_DEFAULT : group);
        cacheManager.removeCache(cacheName);
    }

    /**
     * 清除指定KEY缓存
     * @param group
     * @param key
     * @return boolean
     */
    public boolean removeCache(final String group, final String key){
        Cache cache = getCache(group);
        return cache.remove(key);
    }
    
    /**
     * 获取缓存组名称
     * @return
     */
    public String[] getCacheGroups(){
        return cacheManager.getCacheNames();
    }
    
    /**
     * 获取缓存组中key名称
     * @param group
     * @return
     */
    @SuppressWarnings("unchecked")
	public String[] getCacheNamesByGroup(final String group){
        Cache cache = getCache(group);
        String[] keys=new String[cache.getKeys().size()];
        return (String[]) cache.getKeys().toArray(keys);
    }
    
    /**
     * 
     * @param status 缓存启用模式
     */
    public final void setStatus(final int status) {
        this.status = status;
    }
    
    /**
     * 获取缓存element(ehcache中,velocity模板加载时判断过期时需要)
     * @param key
     * @param group
     * @param timeout
     * @return
     */
    public Object getElementFromCache(String key, String group, long timeout) {
        Cache cache = getCache(group);
        if (null == cache) { // 找不到namspace的cache
            return null;
        }
        Element element = cache.get(key); // 获取指定key的元素
        if (null == element) { // 找不到元素，返回null
            return null;
        }
        if (isTimeOut(element, timeout)) {
            return null;
        }
        if(element.getObjectValue().equals("NULL"))
            return null;
        return element;
    }
    
    
    /**
     * @return 缓存管理器
     */
    public final CacheManager getCacheManager() {
        return cacheManager;
    }

    /**
     * @param cacheManager 缓存管理器
     */
    public void setCacheManager(final CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

}
