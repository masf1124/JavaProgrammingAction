package com.algorithm.pattern.interceptor;

import java.lang.reflect.Method;

/**
 * @program: com.algorithm.pattern.interceptor
 * @author: mashifei
 * @create: 2019-06-04-13
 *
 * before方法返回Boolean值，在真实对象前调用，
 * 当返回true时，则反射真实对象的方法；当返回false时，调用around方法
 *
 * 在反射真实对象的方法或者around方法执行之后，调用after方法。
 */
public interface Interceptor {

    boolean before(Object before, Object target, Method method,Object[] args);

    void around(Object before, Object target, Method method,Object[] args);

    void after(Object before, Object target, Method method,Object[] args);
}
