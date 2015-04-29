package org.zk.redis.detector.ehcache;


public interface StaticCache {
    public static final String CACHE_NAME_DEFAULT = "defaultCache";
    public static final int CACHE_USE_STATUS_DEFAULT = 1; // 缓存状态1:使用缓存;0:更新缓存；2:不使用缓存;3:只使用缓存
    public static final int CACHE_USE = 1; // 正常使用缓存
    public static final int CACHE_UPDATING = 0; // 更新缓存
    public static final int CACHE_NO_USE = 2; // 不使用缓存
    public static final int CACHE_ONLY_USE = 3; // 只使用缓存
    public static final long BASIC_TIME_UNIT = 1000L;//缓存基本时间单位（1000毫秒） 
}
