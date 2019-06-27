package com.algorithm;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-05-25-22
 * 单例模式：懒汉式+同步方法
 */
//使用final防止类被继承
public final class A02_Singleton_lazy_sync {
    /**
     * 懒汉式+同步方法 单例模式优缺点
     * 在多线程环境下保证实例的唯一性，但是同一时刻只能被一个线程访问，性能低下
     * 在需要获取实例的时候初始化，即懒加载
     */
    //实例变量
    private byte[] data = new byte[1024];

    //定义实例，但不直接初始化
    private static A02_Singleton_lazy_sync singleton_lazy = null;

    //私有构造方法，防止类外部使用new
    private A02_Singleton_lazy_sync(){}

    /**
     * 向getInstance方法加入同步控制，每次只有一个线程你能进入
     * synchronized 修饰类方法，对class加锁
     * @return
     */
    public static synchronized A02_Singleton_lazy_sync getInstance(){
        if(null==singleton_lazy){
            singleton_lazy = new A02_Singleton_lazy_sync();
        }
        return singleton_lazy;
    }
}
