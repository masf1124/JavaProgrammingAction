package com.algorithm.pattern.interceptor;

import java.lang.reflect.Method;

/**
 * @program: com.algorithm.pattern.interceptor
 * @author: mashifei
 * @create: 2019-06-04-13
 */
public class MyInterceptor implements Interceptor{
    @Override
    public boolean before(Object before, Object target, Method method, Object[] args) {
        System.out.println("反射方法前逻辑");
        return false;//不反射被代理对象的原有方法
    }

    @Override
    public void around(Object before, Object target, Method method, Object[] args) {
        System.out.println("取代了被代理对象的方法");
    }

    @Override
    public void after(Object before, Object target, Method method, Object[] args) {
        System.out.println("反射方法后的逻辑");
    }
}
