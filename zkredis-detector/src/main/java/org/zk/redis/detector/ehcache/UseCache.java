/**
 * UseCache.java
 *
 * Copyright 2007 easou, Inc. All Rights Reserved.
 */
package org.zk.redis.detector.ehcache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 缓存注解
 * Revision History
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCache {
    long timeout() default -1; // 默认超时时间

    String group() default "defaultCache"; // 默认cache name
}
