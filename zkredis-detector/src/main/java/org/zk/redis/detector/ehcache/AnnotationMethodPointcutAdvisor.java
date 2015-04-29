/**
 * AnnotationMethodPointcutAdvisor.java
 *
 * Copyright 2007 easou, Inc. All Rights Reserved.
 */
package org.zk.redis.detector.ehcache;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.Ordered;

/**
 * 切面定义
 * 
 * Revision History
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial"})
public class AnnotationMethodPointcutAdvisor extends
        StaticMethodMatcherPointcut implements Serializable, PointcutAdvisor,
        Ordered {
    private int order;
    private Advice advice; // 通知
    private Class annotationCls; // 标注

    public AnnotationMethodPointcutAdvisor() {
        order = 0x7fffffff;
    }

    public AnnotationMethodPointcutAdvisor(Advice advice) {
        order = 0x7fffffff;
        this.advice = advice;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public Advice getAdvice() {
        return advice;
    }

    public Pointcut getPointcut() {
        return this;
    }

    public boolean isPerInstance() {
        throw new UnsupportedOperationException(
                "perInstance property of Advisor is not yet supported in Spring");
    }

    public boolean matches(Method method, Class targetClass) {
        return method.getAnnotation(annotationCls) != null;
    }

    public void setAnnotationCls(Class annotationCls) {
        this.annotationCls = annotationCls;
    }

}
