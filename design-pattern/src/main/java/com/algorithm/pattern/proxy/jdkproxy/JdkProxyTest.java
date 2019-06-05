package com.algorithm.pattern.proxy.jdkproxy;

import com.algorithm.pattern.proxy.HelloWorld;
import com.algorithm.pattern.proxy.impl.HelloWorldImpl;

/**
 * @program: com.algorithm.pattern.proxy
 * @author: mashifei
 * @create: 2019-06-04-13
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        JdkProxyExample example = new JdkProxyExample();
        HelloWorld proxy =(HelloWorld) example.bind(new HelloWorldImpl());
        proxy.sayHello();
    }
}
