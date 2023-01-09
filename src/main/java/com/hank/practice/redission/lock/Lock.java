/*
 * Copyright 2021-2021 www.jiahui.com
 */
package com.hank.practice.redission.lock;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * distributed lock annotation
 *
 * @author hank.han
 * @date 2023/01/09 - 14:23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Lock {

    /**
     * 获取锁失败时，不抛异常，但是也不执行业务
     *
     * @return
     */
    boolean ignoreLockFail() default false;

    /**
     * 锁的前缀
     *
     * @return
     */
    String keyPrefix() default "";

    /**
     * 分布式锁的变化key
     *
     * @return
     */
    String key() default "";

    /**
     * 锁的过期时间，单位：秒
     * <br/>leaseTime值大于0
     *
     * @return
     */
    long leaseTime() default 0L;
}
