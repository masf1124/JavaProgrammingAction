package com.algorithm;

import java.util.ServiceLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        ServiceLoader<Hello> loader = ServiceLoader.load(Hello.class);
        for(Hello hello:loader){
            hello.sayHello("big man");
        }
    }
}
