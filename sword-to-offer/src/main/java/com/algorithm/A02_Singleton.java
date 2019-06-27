package com.algorithm;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-05-25-22
 * 单例模式：饿汉式
 */
//使用final修饰，该类不允许被继承
public final class A02_Singleton {

    /**
     * 饿汉式优缺点：
     * 保证多线程环境下的唯一实例
     * 类中的成员如果比较重，会占用大量内存资源，在实例被ClassLoader加载之后，会在堆内存驻留很长时间，
     * 不能懒加载
     */
    private byte[] data = new byte[1024];

    //在定义对象的时候直接初始化类
    private static A02_Singleton singleton = new A02_Singleton();

    //私有构造方法，不允许程序在外部new
    private A02_Singleton(){

    }

    public static A02_Singleton getInstance(){
        return singleton;
    }
}
