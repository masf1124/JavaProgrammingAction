package com.algorithm.pattern.interceptor;

import com.algorithm.pattern.proxy.HelloWorld;
import com.algorithm.pattern.proxy.impl.HelloWorldImpl;

/**
 * @program: com.algorithm.pattern.interceptor
 * @author: mashifei
 * @create: 2019-06-04-14
 */
public class MyInterceptorTest {
    public static void main(String[] args) {
        HelloWorld proxy = (HelloWorld) InterceptorJdkProxy.getProxy(new HelloWorldImpl(),
                "com.algorithm.pattern.interceptor.MyInterceptor");
        proxy.sayHello();
    }
}
