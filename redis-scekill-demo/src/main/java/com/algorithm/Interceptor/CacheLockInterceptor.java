package com.algorithm.Interceptor;

import com.algorithm.Exception.CacheLockException;
import com.algorithm.annotation.CacheLock;
import com.algorithm.annotation.LockedComplexObject;
import com.algorithm.annotation.LockedObject;
import com.algorithm.lock.RedisLock;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: com.algorithm.Interceptor
 * @author: mashifei
 * @create: 2019-05-28-19
 */
public class CacheLockInterceptor implements InvocationHandler {

    public static int ERROR_COUNT = 0;
    private Object proxied;
    private Annotation an;

    public CacheLockInterceptor(Object proxied){
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        CacheLock cacheLock = method.getAnnotation(CacheLock.class);
        //如果没有cachelock pass
        if(null == cacheLock){
            System.out.println("no cachelock annotation.");
            return method.invoke(proxied,args);
        }

        //获得方法中参数的注解
        Annotation[][] annotations = method.getParameterAnnotations();
        //根据获得到的参数注解和参数列表获得加锁的参数
        Object lockedObject = getLockedObject(annotations,args);
        String objectValue = lockedObject.toString();

        //新建一个锁
        RedisLock lock = new RedisLock(cacheLock.lockedPrefix(),objectValue);
        //加锁
        boolean result = lock.lock(cacheLock.timeout(),cacheLock.expireTime());
        if(!result){
            //取锁失败
            ERROR_COUNT++;
            throw new CacheLockException("get lock fail");
        }
        try{
            //加锁成功，执行方法
            return method.invoke(proxied,args);
        }finally {
            lock.unlock();
        }
    }


    private Object getLockedObject(Annotation[][] annotations,Object[] args) throws CacheLockException {
        if(null==args ||args.length ==0){
            throw new CacheLockException("方法参数为空，没有被锁定的对象");
        }

        if(null == annotations || annotations.length==0){
            throw new CacheLockException("没有被注解的参数");
        }

        //不支持多个参数加锁，只支持第一个注解为lockedObjcet或者lockedComplexObject的属性
        int index =-1;//标记参数的位置指针
        for(int i=0;i<annotations.length;i++){
            for(int j=0;j<annotations[i].length;j++){
                //注解为LockedComplexObject
                if(annotations[i][j] instanceof LockedComplexObject){
                    index = i;
                    try{
                        return args[i].getClass()
                                .getField(((LockedComplexObject)annotations[i][j]).field());
                    }catch(NoSuchFieldException | SecurityException f){
                        throw new CacheLockException("注解中没有该属性："+((LockedComplexObject)annotations[i][j]).field());
                    }
                }

                if(annotations[i][j] instanceof LockedObject){
                    index = i;
                    break;
                }
            }
            //找到第一个加注解的参数后，直接break，不支持多参数加锁
            if(index!=-1){
                break;
            }
        }
        if(index==-1){
            throw new CacheLockException("请指定被锁定的参数");
        }
        return args[index];
    }
}
