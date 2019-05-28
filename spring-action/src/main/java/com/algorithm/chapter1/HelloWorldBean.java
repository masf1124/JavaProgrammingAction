package com.algorithm.chapter1;

import org.springframework.stereotype.Component;

/**
 * @program: com.algorithm.chapter1
 * @author: mashifei
 * @create: 2019-05-27-15
 */
@Component
public class HelloWorldBean {
    public String sayHello(){
        return "Hello world!";
    }
}
