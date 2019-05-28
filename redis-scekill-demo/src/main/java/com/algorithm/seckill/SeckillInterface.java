package com.algorithm.seckill;

import com.algorithm.annotation.CacheLock;
import com.algorithm.annotation.LockedObject;

/**
 * @program: com.algorithm.seckill
 * @author: mashifei
 * @create: 2019-05-28-20
 */
public interface SeckillInterface {
    /**
     * 目前只支持在接口方法上注解
     */
    @CacheLock(lockedPrefix = "TEST_PREFIX")
    public void seckill(String userId, @LockedObject long commidityId);
}
