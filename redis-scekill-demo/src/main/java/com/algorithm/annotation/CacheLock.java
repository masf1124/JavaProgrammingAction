package com.algorithm.annotation;

import java.lang.annotation.*;

/**
 * @program: com.algorithm.annotation
 * @author: mashifei
 * @create: 2019-05-28-19
 * 方法级注解，用于会产生并发问题的方法
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {

    String lockedPrefix() default "";//Redis 锁key的前缀
    long timeout() default 2000;//轮询锁的时间
    int expireTime() default 1000;//key在Redis里存在的时间
}
