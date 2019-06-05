package com.algorithm.pattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: com.algorithm.pattern.proxy
 * @author: mashifei
 * @create: 2019-06-04-13
 */
public class JdkProxyExample implements InvocationHandler {

    private Object target = null;


    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("进入代理服务前");
        Object obj = method.invoke(target,args);

        System.out.println("进入代理服务前");
        return obj;
    }
}
