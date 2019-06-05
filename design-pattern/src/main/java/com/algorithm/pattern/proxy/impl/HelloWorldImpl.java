package com.algorithm.pattern.proxy.impl;

import com.algorithm.pattern.proxy.HelloWorld;

/**
 * @program: com.algorithm.pattern.proxy.impl
 * @author: mashifei
 * @create: 2019-06-04-13
 */
public class HelloWorldImpl implements HelloWorld {

    @Override
    public void sayHello() {
        System.out.println("Hello world");
    }
}
