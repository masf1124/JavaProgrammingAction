package com.algorithm.lock;

import com.algorithm.Exception.CacheLockException;
import com.algorithm.factory.RedisFactory;
import com.algorithm.util.RedisClient;
import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @program: com.algorithm.lock
 * @author: mashifei
 * @create: 2019-05-28-20
 */
public class RedisLock {
    /**
     * 加锁方式为
     * lock();
     * try{
     *     executeMethod();
     * }finally{
     *     unlock();
     * }
     */

    //封装Redis
    private static RedisClient redisClient;
    public static final long MILLI_NANO_TIME = 1000*1000;
    public static final String LOCKED ="TRUE";
    public static final Random random = new Random();
    private String key;
    private boolean lock = true;


    public RedisLock(String purpose,String key){
        this.key = purpose+"_"+key+"_lock";
        this.redisClient = RedisFactory.getDefaultClient();
    }

    public RedisLock(String purpose,String key,RedisClient redisClient) {
        this.key = purpose+"_"+key+"_lock";
        this.redisClient = redisClient;
    }


    public boolean lock(long timeout,int expire) throws CacheLockException {
        long nanoTime = System.nanoTime();
        timeout*=MILLI_NANO_TIME;
        try{
            //在timeout时间内不断轮询锁
            while(System.nanoTime()-nanoTime<timeout){
                //锁不存在的话，对字段加锁并设置过期时间，
                if(this.redisClient.setnx(this.key,LOCKED)==1){
                    //设置锁过期时间是为了在没有释放锁的情况下，锁过期后消失，不会造成永久阻塞
                    this.redisClient.expire(this.key,expire);
                    this.lock = true;
                    return this.lock;
                }
                System.out.println("出现锁等待");
                //暂时休眠，避免可能的活锁
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch(Exception e){
            throw new CacheLockException("locking error");
        }
        return false;
    }

    public void unlock(){
        try{
            if(this.lock){
                redisClient.delKey(key);//直接删除
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
